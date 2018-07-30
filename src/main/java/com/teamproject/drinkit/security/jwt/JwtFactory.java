package com.teamproject.drinkit.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtFactory {
    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);

    @Value("${jwt.signingKey}")
    private static String signinKey;

    public String generateToken(){
        return null;
    }
}
