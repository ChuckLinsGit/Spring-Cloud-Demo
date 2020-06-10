//package com.example.cloudclient.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RefreshScope //获取配置
//@RestController
//public class ServerConfigController {
//    @Value("${demo.enviroment.name}")
//    private String name;
//
//    @GetMapping("/getProperty")
//    public String getProperty(){
//        return this.name;
//    }
//}
