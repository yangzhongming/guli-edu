package com.guli.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.guli"})
@MapperScan("com.guli.educms.mapper")
public class EduCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduCmsApplication.class,args);
    }
}
