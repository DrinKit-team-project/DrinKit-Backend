package com.teamproject.drinkit.exception;

public class InvalidJwtException extends RuntimeException{
    public InvalidJwtException(String msg) {
        super(msg);
    }
}
