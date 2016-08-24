package com.jql.yifan.service.impl;

import com.jql.yifan.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by 金奇樑(hzjinqiliang)
 * 16/8/24 19:03
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name + "!";
    }

    @Override
    public long sum(int arg1, int arg2) {
        return arg1 + arg2;
    }
}
