package com.teamproject.drinkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class DrinkitApplication {
    private static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "/app/config/drinkit/real-application.yml";

    public static void main(String[] args) {

        new SpringApplicationBuilder(DrinkitApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }
}
