package com.mergeteam.coincontrol.security.tokenAuth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergeteam.coincontrol.security.tokenAuth.deserializers.TokenCookieJweStringDeserializer;
import com.mergeteam.coincontrol.security.tokenAuth.serializers.TokenCookieJwtStringSerializer;
import com.mergeteam.coincontrol.security.tokenAuth.utils.RequestAuthenticationConverter;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import java.text.ParseException;

@Configuration
public class TokensConfig {

    @Value("${jwt.cookie-token-key}") String cookieTokenKey;

    @Bean
    public TokenCookieJweStringDeserializer cookieTokenDeserializer() throws KeyLengthException, ParseException {
        return new TokenCookieJweStringDeserializer(
                new DirectDecrypter(OctetSequenceKey.parse(cookieTokenKey))
        );
    }

//    @Bean
//    public TokenCookieJwsStringDeserializer jwtTokenDeserializer() throws JOSEException, ParseException {
//        return new TokenCookieJwsStringDeserializer(
//                new MACVerifier(OctetSequenceKey.parse(cookieTokenKey))
//        );
//    }

    @Bean
    public TokenCookieJwtStringSerializer cookieTokenSerializer() throws KeyLengthException, ParseException {
        return new TokenCookieJwtStringSerializer(
                new DirectEncrypter(OctetSequenceKey.parse(cookieTokenKey))
        );
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    TODO: реализовать редирект в случае успешной аутентификации кастомного логина
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return new CustomAuthenticationSuccessHandler();
//    }

}
