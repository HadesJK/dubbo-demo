package com.jql.yifan;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.jql.yifan.service.HelloService;
import com.jql.yifan.service.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Created by 金奇樑(hzjinqiliang)
 * 16/8/24 19:37
 */
public class Consumer {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/dubbo/applicationContext-dubbo.xml");
        if (context == null) {
            System.out.println("Error!");
        }
        MessageService messageService = context.getBean(MessageService.class);
        System.out.println(messageService.sendMessage("jql", "yifan", "Miss you much!"));
        System.out.println(RpcContext.getContext().getUrl());

        HelloService helloService = context.getBean(HelloService.class);
        RpcContext.getContext().setAttachment("ErrorDay", "20140401");
        System.out.println(helloService.sayHello("world"));
        RpcContext.getContext().setAttachment("Method", "sum");
        System.out.println(helloService.sum(1021, 302));
        System.out.println(RpcContext.getContext().getUrl());

        EchoService echoService = (EchoService) messageService;
        System.out.println(echoService.$echo("hades"));
        Assert.hasText("hades");
        System.out.println(RpcContext.getContext().getUrl());
        System.in.read();
    }
}
