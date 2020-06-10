package com.example.cloudconsumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//
//Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具。它是一个基于HTTP和TCP的客户端负载均衡器。
// 它可以通过在客户端中配置ribbonServerList来设置服务端列表去轮询访问以达到均衡负载的作用。
//
// 当Ribbon与Eureka联合使用时，ribbonServerList会被DiscoveryEnabledNIWSServerList重写，扩展成从Eureka注册中心中获取服务实例列表。
// 同时它也会用NIWSDiscoveryPing来取代IPing，它将职责委托给Eureka来确定服务端是否已经启动。
//
// 而当Ribbon与Consul联合使用时，ribbonServerList会被ConsulServerList来扩展成从Consul获取服务实例列表。
// 同时由ConsulPing来作为IPing接口的实现。

@EnableDiscoveryClient//将应用加入Eureka服务治理体系中，需要在application配置文件中设置注册信息，启动后可以在服务端监控页面中看到该消费者
@SpringBootApplication
public class CloudConsumerApplication {

    //初始化RestTemplate，用来真正发起REST请求
    @LoadBalanced//使用Ribbon需要对RestTemplate加LoadBalanced注解，后面就不需使用LoadBalancerClient获取服务
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
         new SpringApplicationBuilder(CloudConsumerApplication.class).web(true).run(args);
    }

}
