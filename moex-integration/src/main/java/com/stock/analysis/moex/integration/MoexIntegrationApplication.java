package com.stock.analysis.moex.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoexIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoexIntegrationApplication.class, args);
    }

}
