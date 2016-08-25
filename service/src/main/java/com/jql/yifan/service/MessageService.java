package com.jql.yifan.service;

/**
 * Created by 金奇樑(hzjinqiliang)
 * 16/8/25 10:04
 */
public interface MessageService {
    /**
     * 发送信息
     * @param from 发送者
     * @param to 接受者
     * @param content 信息内容
     * @return true:发送成功  false:发送失败
     */
    boolean sendMessage(String from, String to, String content);
}
