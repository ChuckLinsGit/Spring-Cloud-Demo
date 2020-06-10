package com.example;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过SwaggerResource添加了多个文档来源，按上面的配置，网关上Swagger会通过访问/swagger-service-a/v2/api-docs和swagger-service-b/v2/api-docs
 * 来加载两个文档内容，同时由于当前应用是Zuul构建的API网关，这两个请求会被转发到swagger-service-a和swagger-service-b服务上的/v2/api-docs接口
 * 获得到Swagger的JSON文档，从而实现汇总加载内容。
 */

@Component
@Primary
public class SwaggerDocConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        ArrayList<SwaggerResource> resourceArrayList = new ArrayList<>();
        //需要对swagger-1和swagger-2注册路由
        resourceArrayList.add(swaggerResource("swagger-1","/swagger-1/v2/api-docs","2.0"));
        resourceArrayList.add(swaggerResource("swagger-2","/swagger-2/v2/api-docs","2.0"));
        return resourceArrayList;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
