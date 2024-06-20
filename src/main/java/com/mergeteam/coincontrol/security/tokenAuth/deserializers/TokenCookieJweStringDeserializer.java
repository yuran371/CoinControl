package com.mergeteam.coincontrol.security.tokenAuth.deserializers;

import com.mergeteam.coincontrol.security.tokenAuth.tokens.CookieToken;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.UUID;

@RequiredArgsConstructor
public class TokenCookieJweStringDeserializer implements TokenDeserializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenCookieJweStringDeserializer.class);

    private final JWEDecrypter jweDecrypter;

    @Override
    public CookieToken deserialize(String string) {
        try {
            EncryptedJWT encryptedJWT = EncryptedJWT.parse(string);
            encryptedJWT.decrypt(this.jweDecrypter);
            JWTClaimsSet claimsSet = encryptedJWT.getJWTClaimsSet();
            return new CookieToken(UUID.fromString(claimsSet.getJWTID()), claimsSet.getSubject(),
                    claimsSet.getStringListClaim("authorities"),
                    claimsSet.getIssueTime().toInstant(),
                    claimsSet.getExpirationTime().toInstant());
        } catch (ParseException | JOSEException exception) {
            LOGGER.error("Could not deserialize Token");
        }
        return null;
    }

}
