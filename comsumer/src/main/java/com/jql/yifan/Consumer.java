package com.jql.yifan;

import com.jql.yifan.service.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 金奇樑(hzjinqiliang)
 * 16/8/24 19:37
 */
public class Consumer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/dubbo/applicationContext-dubbo.xml");
        if (context == null) {
            System.out.println("Error!");
        }
        HelloService helloService = context.getBean(HelloService.class);
        System.out.println(helloService.sayHello("world"));
    }
}
