package com.graduation.design.smartcampususerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SmartCampusUserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartCampusUserserviceApplication.class, args);
    }

}
