package com.teamproject.drinkit;

import com.teamproject.drinkit.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.nio.charset.Charset;

@Configuration
@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class DrinkitApplication {
    private static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.properties";
//            + "/app/config/drinkit/application-realdb.properties";

    public static void main(String[] args) {
        new SpringApplicationBuilder(DrinkitApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run();

    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
}
