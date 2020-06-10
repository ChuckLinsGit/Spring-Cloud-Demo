package com.example.stream.send_receive.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(ISinkSender.class)//必须绑定，否则不会创建队列
public class SinkSender {
    @Autowired
    private ISinkSender sender;

    //使用自定义的管道输出发送消息
    public void send(){
        sender.output().send(MessageBuilder.withPayload("produce a message: from localhost").build());
    }

}
