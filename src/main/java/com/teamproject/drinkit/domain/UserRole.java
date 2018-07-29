package com.teamproject.drinkit.domain;

public enum UserRole {
    USER("USER_ROLE"), ADMIN("ADMIN_ROLE");

    private String name;

    UserRole(String name){
        this.name = name;
    }
}
