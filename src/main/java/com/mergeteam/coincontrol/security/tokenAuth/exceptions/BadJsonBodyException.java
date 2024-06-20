package com.mergeteam.coincontrol.security.tokenAuth.exceptions;

public class BadJsonBodyException extends RuntimeException{
    public BadJsonBodyException(String message) {
        super(message);
    }
}
