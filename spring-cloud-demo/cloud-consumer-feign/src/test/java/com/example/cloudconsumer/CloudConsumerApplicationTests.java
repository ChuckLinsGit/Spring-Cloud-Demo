package com.example.cloudconsumer;

import com.example.cloudconsumer.controller.MyFeignClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CloudConsumerApplication.class)//SpringBootTest和SpringBootApplication取代了SpringApplicationConfiguration注解
@WebAppConfiguration//使用web包必须添加的注解
public class CloudConsumerApplicationTests {
    @Autowired
    private MyFeignClient feignClient;

    /**
     * 执行此测试时会发生异常：
     * org.springframework.beans.factory.BeanCreationNotAllowedException：Error creating bean with name 'eurekaAutoServiceRegistration'
     * 根据Github上的issue说明，这种情况是Eureka的BUG，只在测试环境下发生，而且发生在关闭Server的时候，因此可以忽视
     *链接为：https://github.com/spring-cloud/spring-cloud-netflix/issues/1064
     */
    @Test
    @SneakyThrows
    public void testHandleFileUpload() {

        File file = new File("upload.txt");
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                MediaType.TEXT_PLAIN_VALUE, true, file.getName());

        try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        MultipartFile multi = new CommonsMultipartFile(fileItem);

        log.info(feignClient.handleFileUpload(multi));
    }
}
