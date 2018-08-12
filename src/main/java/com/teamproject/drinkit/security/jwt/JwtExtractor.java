package com.teamproject.drinkit.security.jwt;

import com.teamproject.drinkit.exception.InvalidJwtException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class JwtExtractor {
    private static final String HEADER_PREFIX = "Bearer ";

    public static String extractJwtToken(String header){
        if(StringUtils.isEmpty(header) | header.length() < HEADER_PREFIX.length()) {
            throw new InvalidJwtException("유효한 토큰이 아닙니다.");
        }
        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}
