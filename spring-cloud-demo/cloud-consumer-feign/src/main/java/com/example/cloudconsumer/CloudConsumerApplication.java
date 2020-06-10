package com.example.cloudconsumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


//一套基于Netflix Feign实现的声明式服务调用客户端。它使得编写Web服务客户端变得更加简单。
// 我们只需要通过创建接口并用注解来配置它既可完成对Web服务接口的绑定。它具备可插拔的注解支持，包括Feign注解、JAX-RS注解。
// 它也支持可插拔的编码器和解码器。Spring Cloud Feign还扩展了对Spring MVC注解的支持，同时还整合了Ribbon和Eureka来提供均衡负载的HTTP客户端实现。
//由于Feign是基于Ribbon实现的，所以它自带了客户端负载均衡功能，也可以通过Ribbon的IRule进行策略扩展。
// 另外，Feign还整合的Hystrix来实现服务的容错保护。

@EnableFeignClients//启动Feign客户端
@EnableDiscoveryClient//将应用加入Eureka服务治理体系中，需要在application配置文件中设置注册信息，启动后可以在服务端监控页面中看到该消费者
@SpringBootApplication
public class CloudConsumerApplication {

    public static void main(String[] args) {
         new SpringApplicationBuilder(CloudConsumerApplication.class).web(true).run(args);
    }

}
