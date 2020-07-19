package com.itlike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.itlike.mapper")
public class application {
    public static void main(String[] args) {
        SpringApplication.run(application.class,args);
    }
}
