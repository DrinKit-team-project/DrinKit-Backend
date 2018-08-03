package com.teamproject.drinkit.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamproject.drinkit.security.AccountDetails;
import com.teamproject.drinkit.security.dto.JwtDto;
import com.teamproject.drinkit.security.jwt.JwtFactory;
import com.teamproject.drinkit.security.token.SocialPostAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SocialLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtFactory factory;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
        AccountDetails accountDetails = ((SocialPostAuthorizationToken)auth).getAccountDetails();
        JwtDto jwtDto = generateJwtDto(accountDetails);
        writeResponseMessage(res, jwtDto);
    }

    private JwtDto generateJwtDto(AccountDetails accountDetails) {
        String jwt = factory.generateFrom(accountDetails);
        return new JwtDto(jwt);
    }

    private void writeResponseMessage(HttpServletResponse res, JwtDto jwtDto) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.setStatus(HttpStatus.OK.value());
        res.getWriter().write(objectMapper.writeValueAsString(jwtDto));
    }


}
