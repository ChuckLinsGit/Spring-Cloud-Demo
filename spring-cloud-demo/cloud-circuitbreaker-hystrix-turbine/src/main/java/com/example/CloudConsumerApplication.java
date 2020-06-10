package com.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.context.annotation.Configuration;

@EnableTurbineStream
//@EnableTurbine //启动Turbine对每个服务的Hystrix监控数据进行聚合
@SpringBootApplication
public class CloudConsumerApplication {

    public static void main(String[] args) {
         new SpringApplicationBuilder(CloudConsumerApplication.class).web(true).run(args);
    }
}
