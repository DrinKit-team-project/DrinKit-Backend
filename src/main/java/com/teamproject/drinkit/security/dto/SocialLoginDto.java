package com.teamproject.drinkit.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.teamproject.drinkit.domain.SocialProviders;
import lombok.Getter;

@Getter
public class SocialLoginDto {

    @JsonProperty(value = "provider")
    private SocialProviders provider;

    @JsonProperty(value= "token")
    private String token;

    public SocialLoginDto(){}
    public SocialLoginDto(SocialProviders provider, String token){
        this.provider = provider;
        this.token = token;
    }
}
