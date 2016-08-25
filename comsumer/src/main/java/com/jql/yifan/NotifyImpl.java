package com.jql.yifan;

import org.springframework.stereotype.Service;

/**
 * Created by 金奇樑(hzjinqiliang)
 * 16/8/25 11:46
 */
@Service("notify")
public class NotifyImpl implements Notify {
    @Override
    public void onReturn(String result) {
        System.out.println("on return: " + result);
    }

    @Override
    public void onThrow(Throwable e, int messageId) {
        System.out.println("exception:");
        System.out.println(e.getMessage());
    }

    /**
     * 这个方法没有执行成功
     * @param result
     */
    @Override
    public void onInvoke(String result) {
        System.out.println("on invoke: " + result);
    }
}
