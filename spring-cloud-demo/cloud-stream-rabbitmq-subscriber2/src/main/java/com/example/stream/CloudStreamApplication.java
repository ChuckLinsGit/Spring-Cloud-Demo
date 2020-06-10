package com.example.stream;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//下图是官方文档中对于Spring Cloud Stream应用模型的结构图。
//        从中我们可以看到，Spring Cloud Stream构建的应用程序与消息中间件之间是通过绑定器Binder相关联的，
//        绑定器对于应用程序而言起到了隔离作用，它使得不同消息中间件的实现细节对应用程序来说是透明的。
//        所以对于每一个Spring Cloud Stream的应用程序来说，它不需要知晓消息中间件的通信细节，它只需要知道Binder对应用程序提供的概念去实现即可，
//        而这个概念就是在快速入门中我们提到的消息通道：Channel。
//        如下图案例，在应用程序和Binder之间定义了两条输入通道和两条输出通道来传递消息，而绑定器则是作为这些通道和消息中间件之间的桥梁进行通信。
//
//                |--------------------------------------|
//                |  Spring   cloud Stream Application   |
//                |                                      |
//                |         |------------------|         |
//                |         | Application core |         |
//                |         |------------------|         |
//                |          || ||        || ||          |
//                | inputs   || ||        || ||  outputs |
//                |          || ||        || ||          |
//                |          || ||        || ||          |
//                |        |--------------------|        |
//                |--------|       Binder       |--------|
//                |        |--------------------|        |
//                |                                      |
//                |         |------------------|         |
//                |         |    middleware    |         |
//                |         |------------------|         |
//                |--------------------------------------|
//
//通过定义绑定器作为中间层，完美地实现了应用程序与消息中间件细节之间的隔离。通过向应用程序暴露统一的Channel通道，使得应用程序不需要再考虑各种不同的消息中间件实现。
//当我们需要升级消息中间件，或是更换其他消息中间件产品时，我们要做的就是更换它们对应的Binder绑定器而不需要修改任何Spring Boot的应用逻辑
//另外，Spring Cloud Stream还实现了一个专门用于测试的TestSupportBinder，开发者可以直接使用它来对通道的接收内容进行可靠的测试断言。
//如果要使用除了RabbitMQ和Kafka以外的消息中间件的话，我们也可以通过使用它所提供的扩展API来实现其他中间件的Binder。

@SpringBootApplication
public class CloudStreamApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(
                CloudStreamApplication.class)
                .web(true).run(args);
    }

}
