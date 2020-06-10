package com.example.cloudserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin.server.EnableZipkinServer;

//在zipkin中，将每次请求响应和事务逻辑处理消耗的时间称为span。
//在HTTP请求响应中zipkin有4个标识对应4个阶段
//        client send (cs) ——> server receive(sr) ——> server send(ss) ——> client receive(cr)
//
//在视图中，zipkin详细span一般会比总span要多的原因
//如果向某个服务的某次请求中会产生向别的服务的请求，会产生新的HTTP请求，这时产生的HTTP在zipkin中会经历完整的4个标识阶段，
//即client send (cs) ——> server receive(sr) ——> server send(ss) ——> client receive(cr)，此时该过程会产生2个span:cs&cr,sr&ss
//但本质上还是一次HTTP请求响应，因此最终统计只计算一次span，如下图所示：
//               |Trace：T|          |Trace：T|
//               |span：B1|          |span：B2|
//               |    cs  |          |sr      |
//
//                        |Trace：T|
//                        |span：B |
//           |---------|   -------》  |---------|
//           |Service-A|              |Service-B|
//           |---------|   《-------  |---------|
//                        |Trace：T|
//                        |span：B |
//
//               |     cr |         | ss     |
//               |Trace：T|         |Trace：T |
//               |span：B1|         |span：B2 |
//@EnableZipkinServer //开启Zipkin服务端
//引入stream以使用中间件时需要使用EnableZipkinStreamServer
@EnableZipkinStreamServer
@SpringBootApplication
public class CloudServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CloudServerApplication.class)
                .web(true).run(args);
    }
}
