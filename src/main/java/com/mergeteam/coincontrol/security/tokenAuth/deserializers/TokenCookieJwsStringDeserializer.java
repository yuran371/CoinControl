//package com.mergeteam.coincontrol.security.tokenAuth.deserializers;
//
//import com.mergeteam.coincontrol.security.tokenAuth.tokens.CookieToken;
//import com.nimbusds.jose.JOSEException;
//import com.nimbusds.jose.JWSVerifier;
//import com.nimbusds.jwt.JWTClaimsSet;
//import com.nimbusds.jwt.SignedJWT;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.text.ParseException;
//import java.util.UUID;
//
//@RequiredArgsConstructor
//public class TokenCookieJwsStringDeserializer implements TokenDeserializer {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(TokenCookieJwsStringDeserializer.class);
//
//    private final JWSVerifier jwsVerifier;
//
//    @Override
//    public CookieToken deserialize(String string) {
//        try {
//            SignedJWT signedJWT = SignedJWT.parse(string);
//            if (signedJWT.verify(this.jwsVerifier)) {
//                JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
//                return new CookieToken(UUID.fromString(claimsSet.getJWTID()), claimsSet.getSubject(),
//                        claimsSet.getStringListClaim("authorities"),
//                        claimsSet.getIssueTime().toInstant(),
//                        claimsSet.getExpirationTime().toInstant());
//            }
//        } catch (ParseException | JOSEException exception) {
//            LOGGER.error("Could not deserialize accessToken");
//        }
//        return null;
//    }
//}
