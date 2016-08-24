package com.jql.yifan.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by 金奇樑(hzjinqiliang)
 * 16/8/24 19:31
 */
public class Provider {
    public static void main(String[] args) throws IOException {
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/dubbo/applicationContext-dubbo.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:/spring/applicationContext.xml");
        if (context == null) {
            System.out.println("Error!");
        }
        System.in.read();
    }
}
