package com.teamproject.drinkit.security.dto;

import lombok.Getter;

public class JwtDto {
    private Long id;
    private String token;

    public JwtDto(){}

    public JwtDto( Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }
}
