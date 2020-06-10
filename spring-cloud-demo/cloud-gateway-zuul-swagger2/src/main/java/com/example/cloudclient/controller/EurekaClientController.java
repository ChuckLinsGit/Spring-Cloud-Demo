package com.example.cloudclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClientController {
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/client")
    public String dc() throws InterruptedException {
        //测试Hystrix降级服务
        //Thread.sleep(5000L);
        //获取当前服务提供者缓存的所有服务清单，凡是访问该服务提供者的客户端都将被记录
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }
}