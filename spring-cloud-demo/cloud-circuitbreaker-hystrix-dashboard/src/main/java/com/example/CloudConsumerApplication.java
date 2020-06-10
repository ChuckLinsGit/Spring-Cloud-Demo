package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

//使用Hystrix Dashboard来展示Hystrix用于熔断的各项度量指标。
// 通过Hystrix Dashboard，我们可以方便的查看服务实例的综合情况，比如：服务调用次数、服务调用延迟等
//需要注意的是，如果端点从未被访问，则dashboard将一直loading直到有请求访问
//被监控的端点必须引入hystrix、dashboard和actuator,并使用注释启动了断路器
@EnableHystrixDashboard //启动dashboard
@SpringBootApplication
public class CloudConsumerApplication {

    public static void main(String[] args) {
         new SpringApplicationBuilder(CloudConsumerApplication.class).web(true).run(args);
    }
}
