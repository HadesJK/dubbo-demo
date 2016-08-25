package com.jql.yifan;

/**
 * Created by 金奇樑(hzjinqiliang)
 * 16/8/25 11:47
 */
public interface Notify {

    void onReturn(String result);

    void onThrow(Throwable e, int messageId);

    void onInvoke(String result);
}
