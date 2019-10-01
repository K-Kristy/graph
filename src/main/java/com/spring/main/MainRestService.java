package com.spring.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.spring")
public class MainRestService {
    public static void main(String[] args) {
        SpringApplication.run(MainRestService.class, args);
    }
}
