package com.example.cloudconsumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient//将应用加入Eureka服务治理体系中，需要在application配置文件中设置注册信息，启动后可以在服务端监控页面中看到该消费者
@SpringBootApplication
public class CloudConsumerApplication {

    //初始化RestTemplate，用来真正发起REST请求
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
         new SpringApplicationBuilder(CloudConsumerApplication.class).web(true).run(args);
    }

}
