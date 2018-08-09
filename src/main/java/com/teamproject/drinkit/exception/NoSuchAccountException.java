package com.teamproject.drinkit.exception;

public class NoSuchAccountException extends RuntimeException {
    public NoSuchAccountException(String message) {
        super(message);
    }
}
