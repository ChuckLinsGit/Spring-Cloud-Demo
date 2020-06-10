package com.example.stream.send_receive.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

//消息接收
//启动应用主程序，通过启动日志可以看到如下日志：
//        declaring queue for inbound: input.anonymous.uP8PfHlBSWiaHf9pGwzn9Q, bound to: input
//        Created new connection: rabbitConnectionFactory#2ddb3ae8:0/SimpleConnection@44a085e5 [delegate=amqp://guest@127.0.0.1:5672/, localPort= 13343]
//        started inbound.input.anonymous.uP8PfHlBSWiaHf9pGwzn9Q
//        Adding {message-handler:inbound.input.default} as a subscriber to the 'bridge.input' channel
//        started inbound.input.default
//大致是：通过guest账号在rabbitmq服务中创建一个连接，并声明了一个匿名队列input.anonymous.uP8PfHlBSWiaHf9pGwzn9Q，而guest创建的连接作为消费者与其绑定

//Sink接口源码如下：
//public interface Sink {
//
//    String INPUT = "input";
//
//    @Input(Sink.INPUT)
//    SubscribableChannel input();
//
//}
//它通过@Input注解绑定了一个输入通道指向一个名为input的exchange
//除了Sink之外，Spring Cloud Stream还默认实现了绑定output通道的Source接口，还有结合了Sink和Source的Processor接口
//实际使用时我们也可以自己通过@Input和@Output注解来定义绑定消息通道的接口
//当我们需要为@EnableBinding指定多个接口来绑定消息通道的时候，可以这样定义：@EnableBinding(value = {Sink.class, Source.class})。
//一般而言，一个应用对同一个exchange对输入通道和输出通道分别只有一个，即便有多个类对同一个exchange监听

@EnableBinding({Sink.class,ISinkReceiver.class})//绑定管道，可以在配置文件中设置管道的exchange、消费组和分区
public class SinkSubscriber1 {
    private static Logger logger= LoggerFactory.getLogger(SinkSubscriber1.class);

    //    playload
    @StreamListener(ISinkReceiver.INPUT)//@StreamListener：该注解主要定义在方法上，作用是将被修饰的方法注册为消息中间件上数据流的事件监听器，注解中的属性值对应了监听的消息通道名
    public void receive(Object playload){
        logger.info("Receiver1: "+playload.getClass()+"--"+playload);
    }

    @StreamListener(Sink.INPUT)//@StreamListener：该注解主要定义在方法上，作用是将被修饰的方法注册为消息中间件上数据流的事件监听器，注解中的属性值对应了监听的消息通道名
    public void receive(MyMessage message,Object payload){
        logger.info("Receiver1: "+message);
        logger.info("payload: "+payload);
    }
}
