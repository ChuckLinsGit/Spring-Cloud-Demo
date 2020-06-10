package com.example.cloudconsumer;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//使用服务容错保护的机制的原因：在分布式中，服务提供者和消费者分布在不同的端点上，一旦提供的某个服务端点出现延迟，将会导致请求积压，
// 拖垮这个服务端点，甚至会出现多米诺骨牌效应，将会有更多的服务端点崩溃。

//Spring Cloud Hystrix中实现了线程隔离、断路器等一系列的服务保护功能。它也是基于Netflix的开源框架 Hystrix实现的，
// 该框架目标在于通过控制那些访问远程系统、服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。
// Hystrix具备了服务降级、服务熔断、线程隔离、请求缓存、请求合并以及服务监控等强大功能。

//1、服务降级是指在出现请求延迟的情况下执行指定的策略
//2、服务隔离则是使用了舱壁模式，消费端点对面向不同服务的请求使用不同的关键资源如（线程），这样即便有一个面服务的某个请求出现问题，也不会影响其他请求。
//    Hystrix有两种不同的隔离模式，一种使用不同的线程池来达到隔离目的，一种使用信号量来达到目的，默认使用线程池方式
//        两种模式区别：
//            线程池模式功能更加完整，具备设置超时和异步执行的功能，但因为使用线程池开销大，延迟较高
//            信号量模式与线程池模式相反，开销小、延迟低，但不支持超时和异步的功能
//3、断路器：类似于保险丝，断路器会监控端点情况，当某个服务端点出现问题，触发服务降级，此时断路器会切断它的主逻辑，进入降级逻辑。
//    断路器有三个重要参数：快照时间窗、请求总数下限、错误百分比下限
//        快照时间窗用来统计一些请求和数据，默认时近10s
//        请求总数下限指快照时间内触发断路器的最少请求总数，默认20
//        错误百分比指快照时间内触发断路器的错误请求占请求总数的百分比，默认50%
//    触发断路器的过程：
//        当请求总数大于请求总数下限并且错误比例大于错误百分比则触发断路器
//        触发断路器后，也触发了服务降级，同时，Hystrix会启动休眠时间窗，休眠时间内后面的请求将不再进入主逻辑
//        当休眠时间到了之后，断路器会进入半打开状态，后面的请求会尝试进入主逻辑，如果没有问题则断路器关闭，否则继续进入服务降级

//服务降级、线程隔离和断路器在使用@HystrixCommand时一体设置

//容错保护、服务治理、SpringBoot主类三个基本形成SprinCloud应用
// 因此@EnableHystrix、@EnableDiscoveryClient和@SpringBootConfiguration可用@SpringCloudApplication替用

//@EnableHystrix  //@EnableHystrix和@EnableCircuitBreaker都能启动Hystrix
//@EnableDiscoveryClient//将应用加入Eureka服务治理体系中，需要在application配置文件中设置注册信息，启动后可以在服务端监控页面中看到该消费者
//@SpringBootApplication
@SpringCloudApplication
public class CloudConsumerApplication {

    //因为Hystrix只是容错保护机制，负载均衡机制还是需要保留
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
