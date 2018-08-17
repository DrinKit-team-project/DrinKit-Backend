package com.teamproject.drinkit.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.teamproject.drinkit.security.AccountDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtFactory {
    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);

    private String signingKey;

    public JwtFactory(@Value("${jwt.signingKey}") String signingKey){
        this.signingKey = signingKey;
    }

    private static final String ISSUER = "drinkit";
    private static final String CLAIM_NAME_USERNAME = "USERNAME";
    private static final String CLAIM_NAME_USER_ROLE = "USER_ROLE";

    public String generateFrom(AccountDetails accountDetails) throws JWTCreationException {
        return JWT.create()
                    .withIssuer(ISSUER)
                    .withClaim(CLAIM_NAME_USERNAME, accountDetails.getAccount().getUserId())
                    .withClaim(CLAIM_NAME_USER_ROLE, accountDetails.getAccount().getUserRole().getName())
                    .sign(getAlgorithm());
    }

    private Algorithm getAlgorithm(){
        return Algorithm.HMAC256(signingKey);
    }
}
