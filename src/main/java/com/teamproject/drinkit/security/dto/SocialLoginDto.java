package com.teamproject.drinkit.security.dto;

import lombok.Getter;

@Getter
public class SocialLoginDto {
    private String provider;
    private String token;

    public SocialLoginDto(String provider, String token){
        this.provider = provider;
        this.token = token;
    }
}
