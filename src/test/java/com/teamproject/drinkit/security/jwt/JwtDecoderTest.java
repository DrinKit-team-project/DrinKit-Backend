package com.teamproject.drinkit.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.junit.Assert.*;
@Component
public class JwtDecoderTest {

    private JwtDecoder jwtDecoder;
    private static final Logger log = LoggerFactory.getLogger(JwtDecoderTest.class);

    @Before
    public void setUp() throws Exception {
        String signingKey = "drinkitjwtsecretkey";
        jwtDecoder = new JwtDecoder(signingKey);
    }

    @Test
    public void JWT_TOKEN_DECODE_TEST() {
        DecodedJWT decodedJWT = jwtDecoder.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkcmlua2l0IiwiVVNFUk5BTUUiOiJqaXdvbiIsIlVTRVJfUk9MRSI6IlVTRVJfUk9MRSJ9.boYKwoRzHQ4MZf9YXizmVY02Ibo65F-8Von5uPJoO7o");
       log.debug("Username Claim: {}", decodedJWT.getClaim("USERNAME").asString());
       log.debug("UserRole Claim: {}", decodedJWT.getClaim("USER_ROLE").asString());

    }
}