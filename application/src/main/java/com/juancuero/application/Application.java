package com.juancuero.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.*;

@SpringBootApplication
@EntityScan(basePackages = "com.juancuero.infrastructure.driven_adapter.jpa_repository.entity")
@ComponentScan(basePackages = "com.juancuero")
@EnableJpaRepositories(basePackages = "com.juancuero.infrastructure.driven_adapter.jpa_repository.repository")
@EnableFeignClients(basePackages = "com.juancuero.infrastructure.driven_adapter.feign_client")
@EnableAsync
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
