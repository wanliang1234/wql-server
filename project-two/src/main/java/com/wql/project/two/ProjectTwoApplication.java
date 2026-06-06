package com.wql.project.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjectTwoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectTwoApplication.class, args);
    }
}
