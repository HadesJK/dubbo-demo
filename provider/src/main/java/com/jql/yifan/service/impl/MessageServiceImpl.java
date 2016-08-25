package com.jql.yifan.service.impl;

import com.jql.yifan.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * Created by 金奇樑(hzjinqiliang)
 * 16/8/25 10:06
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Override
    public boolean sendMessage(String from, String to, String content) {
        System.out.println("message from: " + from);
        System.out.println("message to: " + to);
        System.out.println("message content: " + content);
        return true;
    }
}
