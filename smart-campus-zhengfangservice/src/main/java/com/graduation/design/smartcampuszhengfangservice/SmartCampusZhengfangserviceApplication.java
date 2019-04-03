package com.graduation.design.smartcampuszhengfangservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SmartCampusZhengfangserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartCampusZhengfangserviceApplication.class, args);
    }

}
