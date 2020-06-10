package com.example.stream.send_receive.subscribe;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义的管道输出
 */
public interface ISinkSender {
    //因为本例中消费者监听的通道名字为input，因此输出的通道也应该为input
    String OUTPUT="myqueue";

    @Output(ISinkSender.OUTPUT)
    MessageChannel output();
}
