package com.jyan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.jyan.mapper")
@SpringBootApplication
public class JYanCommunityApplication {
    public static void main(String[] args) {
        SpringApplication.run(JYanCommunityApplication.class, args);
    }

}
