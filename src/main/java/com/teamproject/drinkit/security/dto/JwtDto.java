package com.teamproject.drinkit.security.dto;

import lombok.Getter;

@Getter
public class JwtDto {
    private String token;

    public JwtDto(){}

    public JwtDto(String token) {
        this.token = token;
    }
}
