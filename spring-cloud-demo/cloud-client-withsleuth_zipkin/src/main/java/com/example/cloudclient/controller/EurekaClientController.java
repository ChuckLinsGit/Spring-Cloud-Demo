package com.example.cloudclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class EurekaClientController {
    @Autowired
    DiscoveryClient discoveryClient;
    //添加tag、annotation等属性
//    @Autowired
//    Tracer tracer;

    private static Logger logger=LoggerFactory.getLogger(EurekaClientController.class);

    @GetMapping("/client")
    public String dc(HttpServletRequest request) throws InterruptedException {
        //获取sleuth保存在请求头里的信息
        //注意：当还未建立起请求链时下面各个属性都是空的
        //但日志中仍会出现建立起请求链后的各属性，可见第一个节点的属性是在发生请求跳转才产生的
        logger.info("=====<Trace  client:TraceID={},SpanID={},ParentSpanID={},Sampled={},SpanName={}>====",
                request.getHeader("X-B3-TraceId"),request.getHeader("X-B3-SpanId"),request.getHeader("X-B3-ParentSpanId"),
                request.getHeader("X-B3-Sampled"),request.getHeader("X-Span-Name"));

        //添加tag并测试
//        tracer.addTag("myTag","tag");
//        logger.info("=====<Trace  client:tag={}>===",tracer.getCurrentSpan().tags().get("myTag"));

        //测试Hystrix降级服务
        //Thread.sleep(5000L);

        //获取当前服务提供者缓存的所有服务清单，凡是访问该服务提供者的客户端都将被记录
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }
}