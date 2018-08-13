package com.teamproject.drinkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DrinkitApplication {
    private static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "/app/config/drinkit/real-application.yml";

    public static void main(String[] args) {
        String[] appArgs = {"--debug"};
        new SpringApplicationBuilder(DrinkitApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(appArgs);
    }
}
