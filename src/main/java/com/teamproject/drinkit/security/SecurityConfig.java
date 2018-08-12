package com.teamproject.drinkit.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamproject.drinkit.security.filter.JwtAuthenticationFilter;
import com.teamproject.drinkit.security.filter.SocialLoginFilter;
import com.teamproject.drinkit.security.filter.SuitableUrlMatcher;
import com.teamproject.drinkit.security.handler.JwtAuthenticationFailuerHandler;
import com.teamproject.drinkit.security.handler.JwtAuthenticationSuccessHandler;
import com.teamproject.drinkit.security.handler.SocialLoginSuccessHandler;
import com.teamproject.drinkit.security.provider.JwtAuthenticationProvider;
import com.teamproject.drinkit.security.provider.SocialLoginAuthenticationProvider;
import com.teamproject.drinkit.service.KakaoInfoFetchServiceImp;
import com.teamproject.drinkit.service.SocialInfoFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SocialLoginSuccessHandler socialLoginSuccessHandler;

    @Autowired
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Autowired
    private JwtAuthenticationFailuerHandler jwtAuthenticationFailuerHandler;

    @Autowired
    private SuitableUrlMatcher suitableUrlMatcher;

    @Autowired
    private SocialLoginAuthenticationProvider socialProvider;

    @Autowired
    private JwtAuthenticationProvider jwtProvider;

    @Bean
    public ObjectMapper getObjectMapper(){ return new ObjectMapper();}

    public SocialLoginFilter socialLoginFilter() throws Exception {
        SocialLoginFilter filter = new SocialLoginFilter("/social", this.socialLoginSuccessHandler);
        filter.setAuthenticationManager(super.authenticationManagerBean());
        return filter;
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(suitableUrlMatcher,  jwtAuthenticationSuccessHandler, jwtAuthenticationFailuerHandler);
        filter.setAuthenticationManager(super.authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(this.socialProvider)
                .authenticationProvider(this.jwtProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();

        http
                .authorizeRequests()
                .antMatchers("/h2-console**").permitAll();
        http
                .addFilterBefore(socialLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}