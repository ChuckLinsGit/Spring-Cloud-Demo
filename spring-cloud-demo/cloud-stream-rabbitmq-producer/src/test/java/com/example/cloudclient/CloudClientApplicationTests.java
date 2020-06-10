package com.example.cloudclient;

import com.example.stream.CloudStreamApplication;
import com.example.stream.send_receive.subscribe.SinkSender;
import com.example.stream.send_receive.subscribe.SourceSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CloudStreamApplication.class)
public  class CloudClientApplicationTests {

    @Autowired
    private SinkSender sender;

    @Test
    public void testSender(){
        sender.send();
    }

    @Autowired
    private SourceSender sourceSender;

    @Test
    public void testSourceSend(){
        sourceSender.sendDelay();
    }
}
