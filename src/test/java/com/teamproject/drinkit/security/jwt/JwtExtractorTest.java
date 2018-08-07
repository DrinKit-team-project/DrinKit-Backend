package com.teamproject.drinkit.security.jwt;

import com.teamproject.drinkit.exception.InvalidJwtException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JwtExtractorTest {

    @Test
    public void EXTRACT_JWT_TOKEN_TEST() {
        String header = "Bearer yh86QMH1UU8Ylb4hriy6He6bikzZyowMfDXYQQopdaYAAAFk_ca-wQ";
        String token = "yh86QMH1UU8Ylb4hriy6He6bikzZyowMfDXYQQopdaYAAAFk_ca-wQ";
        assertThat(JwtExtractor.extractJwtToken(header), is(token));
    }

    @Test(expected = InvalidJwtException.class)
    public void EXTRACT_JWT_FAIL_TEST() {
        String header = "";
        JwtExtractor.extractJwtToken(header);
    }
}