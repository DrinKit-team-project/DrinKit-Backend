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
        String signingKey = "testkey";
        jwtDecoder = new JwtDecoder(signingKey);
    }

    @Test
    public void JWT_TOKEN_DECODE_TEST() {
        DecodedJWT decodedJWT = jwtDecoder.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkcmlua2l0IiwiVVNFUk5BTUUiOiJqaXdvbiIsIlVTRVJfUk9MRSI6IlJPTEVfVVNFUiJ9.Juz2LTaWs62aJYOioH4ht-9gqwLhaTiHzNAsnZp7owg");
       log.debug("Username Claim: {}", decodedJWT.getClaim("USERNAME").asString());
       log.debug("UserRole Claim: {}", decodedJWT.getClaim("USER_ROLE").asString());

    }
}