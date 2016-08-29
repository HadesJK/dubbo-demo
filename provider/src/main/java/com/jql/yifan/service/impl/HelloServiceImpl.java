package com.jql.yifan.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
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
        System.out.println(RpcContext.getContext().getAttachment("ErrorDay"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name + "!";
    }

    @Override
    public long sum(int arg1, int arg2) {
        if ("multi".equals(RpcContext.getContext().getAttachment("Method")))
            return arg1 * arg2;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return arg1 + arg2;
    }
}
