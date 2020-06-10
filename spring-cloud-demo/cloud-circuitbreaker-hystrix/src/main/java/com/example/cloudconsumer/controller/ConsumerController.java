package com.example.cloudconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    //将controller响应方法的逻辑封装成一个Service类，对Service类进行容错保护改造
    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/consume")
    public String consume(){
        return consumerService.consume();
    }
}
