package com.mergeteam.coincontrol.integration.security.tokenAuth.serializers;

import com.mergeteam.coincontrol.CoinControlApplication;
import com.mergeteam.coincontrol.security.tokenAuth.serializers.TokenCookieJwtStringSerializer;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.CookieToken;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.factories.DefaultJWEDecrypterFactory;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(classes = CoinControlApplication.class)
@ExtendWith(MockitoExtension.class)
class TokenCookieJwtStringSerializerTest {

//    private static final DefaultJWEDecrypterFactory DECRYPTER_FACTORY = new DefaultJWEDecrypterFactory();

    @Autowired
    private TokenCookieJwtStringSerializer tokenCookieJwtStringSerializer;

    @Mock
    private JWEEncrypter jweEncrypter;

    @Value("${jwt.cookie-token-key}") String secretKeyJson;

    @Mock
    private JWEDecrypter jweDecrypter;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        OctetSequenceKey octetSequenceKey = OctetSequenceKey.parse(secretKeyJson);
        this.jweEncrypter = new DirectEncrypter(octetSequenceKey);
        this.tokenCookieJwtStringSerializer = new TokenCookieJwtStringSerializer(this.jweEncrypter);
        jweDecrypter = new DirectDecrypter(octetSequenceKey);
    }

    @Test
    void testApply() throws Exception {
        // Prepare token object
        UUID tokenId = UUID.randomUUID();
        String username = "testuser";
        List<String> authorities = List.of("ROLE_USER");
        Instant createdAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiresAt = createdAt.plusSeconds(3600); // 1 hour TTL

        Token token = new CookieToken(tokenId, username, authorities, createdAt, expiresAt);

        String jwtString = tokenCookieJwtStringSerializer.apply(token);

        assertThat(jwtString).isNotNull();

        EncryptedJWT encryptedJWT = EncryptedJWT.parse(jwtString);

//        encryptedJWT.decrypt(new DirectEncrypter(secretKey)); // Decrypt using the same key   // GPT
//        encryptedJWT.decrypt(jweDecrypter);     // в TokenCookieJweStringDeserializer
//        int length1 = secretKey.length();
//        int length = secretKey.getBytes().length;
//        Key key = new SecretKeySpec(secretKey.getBytes(),0, length,"DES");  // спизжено у https://github.com/GluuFederation/oxAuth/blob/08483bfa10151ec82af95bede6a9c2cb307e06e1/Model/src/main/java/org/gluu/oxauth/model/jwe/JweDecrypterImpl.java#L109
//        JWEDecrypter decrypter = DECRYPTER_FACTORY.createJWEDecrypter(encryptedJWT.getHeader(), key);
        // TODO: разобраться с DefaultJWEDecrypterFactory DECRYPTER_FACTORY
        encryptedJWT.decrypt(jweDecrypter);
        JWTClaimsSet claimsSet = encryptedJWT.getJWTClaimsSet();
        assertThat(claimsSet.getJWTID()).isEqualTo(tokenId.toString());
        assertThat(claimsSet.getSubject()).isEqualTo(username);
        assertThat(claimsSet.getIssueTime().toInstant()).isEqualTo(createdAt);
        assertThat(claimsSet.getExpirationTime().toInstant()).isEqualTo(expiresAt);
        assertThat(claimsSet.getStringListClaim("authorities")).containsExactly("ROLE_USER");
    }

}