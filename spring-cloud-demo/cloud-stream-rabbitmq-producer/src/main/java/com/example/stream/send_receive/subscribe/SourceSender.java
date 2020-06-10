package com.example.stream.send_receive.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(value = Source.class)//必须绑定，否则不会创建队列
public class SourceSender {

    private static Logger logger= LoggerFactory.getLogger(SourceSender.class);

//    @Bean
//    @InboundChannelAdapter(value = Source.OUTPUT,poller = @Poller(fixedDelay = "2000"))
//    public MessageSource<String> sendDelay(){
//        return ()->new GenericMessage<>("{\"message\" : \"chuck send a message : "+(int)(Math.random()*100)+"\"}");
//    }

    /**
     * 消息分区测试
     * @return
     */
    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT,poller = @Poller(fixedDelay = "2000"))
    public MessageSource<String> sendDelay(){
        return ()->new GenericMessage<>("{\"message\" : \"chuck wanna send a message \"}");
    }
}
