package com.example.cloudconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    //负载均衡客户端
    @Autowired
    private LoadBalancerClient balancerClient;

    //REST请求发起模板
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/consume")
    public String consume(){
        //获取相对应的服务
        ServiceInstance client = balancerClient.choose("client");
        String url="http://"+client.getHost()+":"+client.getPort()+"/"+"client";
        System.out.println(url);
        //根据服务URL发起请求并设定响应类型
        return restTemplate.getForObject(url,String.class);
    }
}
