package com.teamproject.drinkit.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.teamproject.drinkit.domain.Account;
import com.teamproject.drinkit.domain.AccountRepository;
import com.teamproject.drinkit.exception.InvalidJwtException;
import com.teamproject.drinkit.exception.NoLoginedUserException;
import com.teamproject.drinkit.security.AccountDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

    private String signingKey;
    private static final Logger log = LoggerFactory.getLogger(JwtDecoder.class);

    @Autowired
    private AccountRepository accountRepository;

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

    public Account findLoginedUser(String header) {
        AccountDetails accountDetails = decode(JwtExtractor.extractJwtToken(header));
        log.debug("유저이름 : {}", accountDetails.getUsername());
        return accountRepository.findByUserId(accountDetails.getUsername()).orElseThrow(() -> new NoLoginedUserException("현재 로그인한 유저가 존재하지 않습니다."));
    }

}
