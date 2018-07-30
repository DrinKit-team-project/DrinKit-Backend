package com.teamproject.drinkit.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public enum UserRole {
    USER("USER_ROLE"), ADMIN("ADMIN_ROLE");

    private String name;

    UserRole(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
