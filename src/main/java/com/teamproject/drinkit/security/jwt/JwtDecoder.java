package com.teamproject.drinkit.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.teamproject.drinkit.domain.AccountRepository;
import com.teamproject.drinkit.exception.InvalidJwtException;
import com.teamproject.drinkit.security.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

    private String signingKey;

    public JwtDecoder(@Value("${jwt.signingKey}") String signingKey){
        this.signingKey = signingKey;
    }

    public AccountDetails decode(String jwtToken){
        return AccountDetails.fromDecodedJwt(verify(jwtToken));
    }

    protected DecodedJWT verify(String jwtToken) {
        DecodedJWT decodedJWT = null;
        try{
            Algorithm algorithm = Algorithm.HMAC256(signingKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            decodedJWT = verifier.verify(jwtToken);
        }catch (JWTVerificationException exception){
            throw new InvalidJwtException("올바른 토큰이 아닙니다.");
        }
        return decodedJWT;
    }

}
