package com.mergeteam.coincontrol.security.tokenAuth.deserializers;

import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;

public interface TokenDeserializer {

    Token deserialize(String token);
}
