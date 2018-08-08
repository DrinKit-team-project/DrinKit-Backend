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

    @JsonProperty(value = "id")
    private String id;

    public SocialLoginDto(){}

    public SocialLoginDto(SocialProviders provider, String token, String id) {
        this.provider = provider;
        this.token = token;
        this.id = id;
    }
}
