package com.teamproject.drinkit;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> registerCharacterEncodingFilterBean() throws Exception {
        FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean = new FilterRegistrationBean<>(characterEncodingFilter());
        filterRegistrationBean.setName("CharacterEncodingFilter");
        filterRegistrationBean.addUrlPatterns("/api/**");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    private CharacterEncodingFilter characterEncodingFilter() throws  Exception{
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
