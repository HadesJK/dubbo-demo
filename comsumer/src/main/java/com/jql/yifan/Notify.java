package com.jql.yifan;

/**
 * Created by 金奇樑(hzjinqiliang)
 * 16/8/25 11:47
 */
public interface Notify {

    /**
     * onreturn 事件处理器
     * @param result 方法调用返回的结果
     */
    void onReturn(String result);

    /**
     * onthrow 事件处理器
     * @param e 服务提供者抛出的异常
     * @param messageId 消费者请求服务提供者的参数
     */
    void onThrow(Throwable e, int messageId);

    void onInvoke(String result);
}
