package com.jql.yifan.service;
/**
 * Created by 金奇樑(hzjinqiliang)
 * 16/8/24 17:32
 */
public interface HelloService {

    /**
     * 返回 hello ${name}
     * @param name 入参
     * @return hello + name
     */
    String sayHello(String name);

    /**
     * 计算2个数之和
     * @param arg1 加数
     * @param arg2 被加数
     * @return arg1 + arg2
     */
    long sum(int arg1, int arg2);
}
