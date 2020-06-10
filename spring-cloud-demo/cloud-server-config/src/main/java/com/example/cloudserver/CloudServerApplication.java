package com.example.cloudserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableDiscoveryClient和@EnableEurekaClient两个注解的功能基本是一致，都是注册服务
//区别在于，@EnableEurekaClient只能在Eureka中使用，而@EnableDiscoveryClient可以用于所有服务注册框架

@EnableDiscoveryClient//注册服务
@EnableConfigServer  //开启Spring Cloud Config的服务端功能
@SpringBootApplication
public class CloudServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CloudServerApplication.class)
                .web(true).run(args);
    }
}
