package com.teamproject.drinkit.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamproject.drinkit.security.dto.SocialLoginDto;
import com.teamproject.drinkit.security.token.SocialPreAuthorizationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SocialLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationSuccessHandler successHandler;

    protected SocialLoginFilter(String defaultFilterProcessesUrl) { super(defaultFilterProcessesUrl); }

    public SocialLoginFilter(String url, AuthenticationSuccessHandler successHandler){
        super(url);
        this.successHandler = successHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
        // TODO : 클라이언트로부터 받은 Provider 정보와 Access Token 을 PreToken 에 담아 Provider 로 전달한다.
        ObjectMapper mapper = new ObjectMapper();

        SocialLoginDto socialLoginDto = mapper.readValue(req.getReader(), SocialLoginDto.class);
        SocialPreAuthorizationToken token = new SocialPreAuthorizationToken(socialLoginDto);
        return super.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.successHandler.onAuthenticationSuccess(req, res, authResult);
    }
}
