package com.teamproject.drinkit.security.dto;

import lombok.Getter;

public class JwtDto {
    private String token;

    public JwtDto(){}

    public JwtDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
