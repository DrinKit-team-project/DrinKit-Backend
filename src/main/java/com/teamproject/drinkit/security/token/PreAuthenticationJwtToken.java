package com.teamproject.drinkit.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthenticationJwtToken extends UsernamePasswordAuthenticationToken {
    public PreAuthenticationJwtToken(String token) {
        super(token, token);
    }

    public String getToken(){
        return (String)getPrincipal();
    }
}
