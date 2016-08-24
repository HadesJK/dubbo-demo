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

- <dubbo:method/> 方法配置，用于ServiceConfig和ReferenceConfig指定方法级的配置信息。

- <dubbo:argument/> 用于指定方法参数配置。

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



