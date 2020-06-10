package com.example.cloudconsumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ConsumerController {

//    使用Ribbon不需要负载均衡客户端，Ribbon会有一个服务列表，能直接根据服务名查找服务
//    @Autowired
//    private LoadBalancerClient balancerClient;

    //REST请求发起模板
    @Autowired
    private RestTemplate restTemplate;

    private static Logger logger= LoggerFactory.getLogger(ConsumerController.class);

    @GetMapping("/consume")
    public String consume(HttpServletRequest request){
        //获取sleuth保存在请求头里的信息
        //注意：当还未建立起请求链时下面各个属性都是空的
        //但日志中仍会出现建立起请求链后的各属性，可见第一个节点的属性是在发生请求跳转才产生的
        logger.info("=====<Trace  ribbon:TraceID={},SpanID={},ParentSpanID={},Sampled={},SpanName={}>====",
                    request.getHeader("X-B3-TraceId"),request.getHeader("X-B3-SpanId"),request.getHeader("X-B3-ParentSpanId"),
                    request.getHeader("X-B3-Sampled"),request.getHeader("X-Span-Name"));
        //获取相对应的服务
        String url="http://client/client";
        //根据服务URL发起请求并设定响应类型
        return restTemplate.getForObject(url,String.class);
    }
}
