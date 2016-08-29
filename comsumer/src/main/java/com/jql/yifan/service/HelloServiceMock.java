package com.jql.yifan.service;

/**
 * Created by 金奇樑(hzjinqiliang)
 * 16/8/25 21:20
 */
public class HelloServiceMock implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("mockmockmock");
        return null;
    }

    @Override
    public long sum(int arg1, int arg2) {
        return 0;
    }
}
