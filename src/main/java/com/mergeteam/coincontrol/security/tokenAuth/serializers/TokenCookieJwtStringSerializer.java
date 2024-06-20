package com.mergeteam.coincontrol.security.tokenAuth.serializers;

import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import com.nimbusds.jose.*;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.function.Function;

@RequiredArgsConstructor
public class TokenCookieJwtStringSerializer implements Function<Token, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenCookieJwtStringSerializer.class);

    private final JWEEncrypter jweEncrypter;

    private JWEAlgorithm jweAlgorithm = JWEAlgorithm.DIR;

    private EncryptionMethod encryptionMethod = EncryptionMethod.A256GCM;
    @Override
    public String apply(Token token) {
        JWEHeader jweHeader = new JWEHeader.Builder(this.jweAlgorithm, this.encryptionMethod)
                .keyID(token.getId().toString())
                .build();
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .jwtID(token.getId().toString())
                .subject(token.getSubject())
                .issueTime(Date.from(token.getCreatedAt()))
                .expirationTime(Date.from(token.getExpiresAt()))
                .claim("authorities", token.getAuthorities())
                .build();
        LOGGER.info("Claims Set: " + claimsSet.toJSONObject().toString());
        EncryptedJWT encryptedJWT = new EncryptedJWT(jweHeader, claimsSet);
        try {
            encryptedJWT.encrypt(this.jweEncrypter);

            String serialize = encryptedJWT.serialize();
            return serialize;
        } catch (JOSEException exception) {
            LOGGER.error(exception.getMessage(), exception);
        }
        return null;
    }
}