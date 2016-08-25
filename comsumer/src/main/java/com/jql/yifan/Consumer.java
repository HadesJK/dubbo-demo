package com.jql.yifan;

import com.jql.yifan.service.HelloService;
import com.jql.yifan.service.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

        HelloService helloService = context.getBean(HelloService.class);
        System.out.println(helloService.sayHello("world"));
        System.out.println(helloService.sum(1021, 302));

        System.in.read();
    }
}
