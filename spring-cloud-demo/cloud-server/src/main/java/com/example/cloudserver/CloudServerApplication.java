package com.example.cloudserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer //启动Eureka服务端，但需要禁用本身注册客户端的行为，在application配置文件中设置
@SpringBootApplication
public class CloudServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CloudServerApplication.class)
                .web(true).run(args);
    }
}
