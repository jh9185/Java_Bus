package com.example.java_bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages="com.example.java_bus")
public class JavaBusApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaBusApplication.class, args);
    }

}
