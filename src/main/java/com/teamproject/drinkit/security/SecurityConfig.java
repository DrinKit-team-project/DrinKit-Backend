package com.teamproject.drinkit.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamproject.drinkit.security.filter.SocialLoginFilter;
import com.teamproject.drinkit.security.handler.SocialLoginSuccessHandler;
import com.teamproject.drinkit.security.provider.SocialLoginAuthenticationProvider;
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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SocialLoginSuccessHandler socialLoginSuccessHandler;

    @Autowired
    private SocialLoginAuthenticationProvider socialProvider;

    @Bean
    public ObjectMapper getObjectMapper(){ return new ObjectMapper();}

    public SocialLoginFilter socialLoginFilter() throws Exception {
        SocialLoginFilter filter = new SocialLoginFilter("/social", this.socialLoginSuccessHandler);
        filter.setAuthenticationManager(super.authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(socialProvider);
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
                .addFilterBefore(socialLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
