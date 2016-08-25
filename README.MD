#dubbo

[http://dubbo.io/]

[TOC]

## service工程是api，provider和consumer共享

## provider工程是服务提供者，依赖service


## consumer工程是服务消费者，依赖service

## dubbo几个重要的标签

- <dubbo:service/> 
服务配置，用于暴露一个服务，定义服务的元信息，一个服务可以用多个协议暴露，一个服务也可以注册到多个注册中心。

- <dubbo:reference/> 
引用配置，用于创建一个远程服务代理，一个引用可以指向多个注册中心。

- <dubbo:protocol/> 协议配置，用于配置提供服务的协议信息，协议由提供方指定，消费方被动接受。

- <dubbo:application/> 
应用配置，用于配置当前应用信息，不管该应用是提供者还是消费者。

- <dubbo:module/> 
模块配置，用于配置当前模块信息，可选。

- <dubbo:registry/> 
注册中心配置，用于配置连接注册中心相关信息。

- <dubbo:monitor/> 
监控中心配置，用于配置连接监控中心相关信息，可选。

- <dubbo:provider/> 提供方的缺省值，当ProtocolConfig和ServiceConfig某属性没有配置时，采用此缺省值，可选。

- <dubbo:consumer/>
消费方缺省配置，当ReferenceConfig某属性没有配置时，采用此缺省值，可选。

- <dubbo:method/> 
方法配置，用于ServiceConfig和ReferenceConfig指定方法级的配置信息。

- <dubbo:argument/> 
用于指定方法参数配置。

## 参数加载优先级

![](img/dubbo-config-override.jpg)

总结：
上图中，蓝色的代表消费者的配置参数，青色的是提供者的配置参数，可以看出同一个位置的参数，消费的优先级比提供者的高。
- 方法级优先，接口级次之，全局配置再次之。
- 如果级别一样，则消费方优先，提供方次之。

duubo开发者给出的建议：
**其中，服务提供方配置，通过URL经由注册中心传递给消费方。**

**建议由服务提供方设置超时，因为一个方法需要执行多长时间，服务提供方更清楚，如果一个消费方同时引用多个服务，就不需要关心每个服务的超时设置。**

理论上ReferenceConfig的非服务标识配置，在ConsumerConfig，ServiceConfig, ProviderConfig均可以缺省配置。

## 负载均衡

dubbo 默认实现了以下几种负载均衡算法，默认采用随机算法：

- **Random LoadBalance**
随机，按权重设置随机概率。
在一个截面上碰撞的概率高，但调用量越大分布越均匀，而且按概率使用权重后也比较均匀，有利于动态调整提供者权重。

- **RoundRobin LoadBalance**
轮循，按公约后的权重设置轮循比率。
存在慢的提供者累积请求问题，比如：第二台机器很慢，但没挂，当请求调到第二台时就卡在那，久而久之，所有请求都卡在调到第二台上。


- **LeastActive LoadBalance**
最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差。
使慢的提供者收到更少请求，因为越慢的提供者的调用前后计数差会越大。


- **ConsistentHash LoadBalance**
一致性Hash，相同参数的请求总是发到同一提供者。
当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动。算法参见：http://en.wikipedia.org/wiki/Consistent_hashing。
缺省只对第一个参数Hash，如果要修改，请配置
**<dubbo:parameter key="hash.arguments" value="0,1" />**
缺省用160份虚拟节点，如果要修改，请配置
**<dubbo:parameter key="hash.nodes" value="320" />**

负载均衡可以配置在服务提供者端，也可以配置在服务消费者端，细粒度在接口级别和方法级别。
官方给出如下配置：
```xml
// 服务提供者配置负载均衡，消费者被动接受，细粒度：接口级别
<dubbo:service interface="..." loadbalance="roundrobin" />
// 消费者主动配置负载均衡，细粒度：接口级别
<dubbo:reference interface="..." loadbalance="roundrobin" />
// 服务提供者配置负载均衡，消费者被动接受，细粒度：方法级别
<dubbo:service interface="...">
    <dubbo:method name="..." loadbalance="roundrobin"/>
</dubbo:service>
// 消费者主动配置负载均衡，细粒度：方法界别
<dubbo:reference interface="...">
    <dubbo:method name="..." loadbalance="roundrobin"/>
</dubbo:reference>
```

**那么问题来了，服务提供者和消费者的配置，谁的优先级别高？个人觉得和之前的参数优先级别一样，服务消费者高于服务提供者的配置；方法级别高于接口级别。**


## 线程模型

事件处理线程说明

- 如果事件处理的逻辑能迅速完成，并且不会发起新的IO请求，比如只是在内存中记个标识，则直接在IO线程上处理更快，因为减少了线程池调度。

- 但如果事件处理逻辑较慢，或者需要发起新的IO请求，比如需要查询数据库，则必须派发到线程池，否则IO线程阻塞，将导致不能接收其它请求。

- 如果用IO线程处理事件，又在事件处理过程中发起新的IO请求，比如在连接事件中发起登录请求，会报“可能引发死锁”异常，但不会真死锁。

![](img/dubbo-protocol.jpg)

**Dispatcher**

- all 
所有消息都派发到线程池，包括请求，响应，连接事件，断开事件，心跳等。

- direct 
所有消息都不派发到线程池，全部在IO线程上直接执行。

- message 
只有请求响应消息派发到线程池，其它连接断开事件，心跳等消息，直接在IO线程上执行。

- execution 只请求消息派发到线程池，不含响应，响应和其它连接断开事件，心跳等消息，直接在IO线程上执行。

- connection 
在IO线程上，将连接断开事件放入队列，有序逐个执行，其它消息派发到线程池。

**ThreadPool**

这个和java的ExecutorService执行框架很像。

- fixed 
固定大小线程池，启动时建立线程，不关闭，一直持有。(缺省)

- cached 
缓存线程池，空闲一分钟自动删除，需要时重建。

- limited 
可伸缩线程池，但池中的线程数只会增长不会收缩。(为避免收缩时突然来了大流量引起的性能问题)。

从上面可以看出，这个配置是全局的，而且是配置在服务提供者这一端的。

