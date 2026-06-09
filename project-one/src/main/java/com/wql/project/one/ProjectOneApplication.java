package com.wql.project.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjectOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectOneApplication.class,args);
    }
}
