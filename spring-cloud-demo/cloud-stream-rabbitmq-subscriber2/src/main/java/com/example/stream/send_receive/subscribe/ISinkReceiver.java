package com.example.stream.send_receive.subscribe;

import  org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义输入管道
 */
public interface ISinkReceiver {
    String INPUT= "myqueue";

    @Input(value = ISinkReceiver.INPUT)
    SubscribableChannel input();
}
