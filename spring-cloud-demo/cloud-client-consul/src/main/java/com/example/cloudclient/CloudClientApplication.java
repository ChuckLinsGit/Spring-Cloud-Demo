package com.example.cloudclient;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(
                CloudClientApplication.class)
                .web(true).run(args);
    }

}
