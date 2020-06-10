package com.example.cloudconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 测试服务降级的方法，服务降级的表现即是响应延时，因此只要服务提供端方法设置延迟即可
 */
@Component
public class ConsumerService {
    //REST请求发起模板
    @Autowired
    private RestTemplate restTemplate;

    //指定服务降级后的执行的方法
    @HystrixCommand(fallbackMethod = "fallback")
    public String consume(){
        //获取相对应的服务
        String url="http://client/client";
        //根据服务URL发起请求并设定响应类型
        return restTemplate.getForObject(url,String.class);
    }

    //服务降级后处理方法
    public String fallback(){
        return "fallback";
    }
}
