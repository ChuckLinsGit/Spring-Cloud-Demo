package com.example;

import com.spring4all.swagger.EnableSwagger2Doc;
import  org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

//Zuul框架为系统提供了REST API到服务端点的映射关系，并且也提供了过滤器功能，
//通过在访问最前端设置路由的方式解决了
//SpringCloud框架因缺少权限控制不得不在原有接口添加检校逻辑却污染了系统业务逻辑并且破坏了REST API无状态（不得不使用了session）特点的问题
//注意：REST API无状态的特性指服务端自由的状态，应用服务器无需保存客户端的信息

@EnableSwagger2Doc  //开启Swagger功能
@EnableZuulProxy    //开启Zuul路由功能
@SpringBootApplication
public class CloudGatewayApplication {

    //使用Zuul过滤器
//    @Bean
//    public ZuulFilterImpl zuulFilterConfig(){
//        return new ZuulFilterImpl();
//    }

    public static void main(String[] args) {
         new SpringApplicationBuilder(CloudGatewayApplication.class).web(true).run(args);
    }
}
