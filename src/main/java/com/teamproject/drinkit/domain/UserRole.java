package com.teamproject.drinkit.domain;

import com.teamproject.drinkit.exception.NoSuchAuthoritiesException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

public enum UserRole {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private String name;

    UserRole(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static UserRole getRoleByName(String roleName) {
        return Arrays.stream(UserRole.values()).filter(r -> r.getName().equals(roleName)).findFirst().orElseThrow(() -> new NoSuchAuthoritiesException("검색된 권한이 없습니다."));
    }
}
