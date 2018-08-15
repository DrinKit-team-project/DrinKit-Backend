package com.teamproject.drinkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class WebMvcConfig {
    @Autowired
    private Environment env;

    @Primary
    @Bean
    public DataSource customDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.developdatasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.developdatasource.url"));
        dataSource.setUsername(env.getProperty("spring.developdatasource.username"));
        dataSource.setPassword(env.getProperty("spring.developdatasource.password"));

        return dataSource;

    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
