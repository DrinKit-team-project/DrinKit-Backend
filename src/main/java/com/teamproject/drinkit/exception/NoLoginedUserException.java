package com.teamproject.drinkit.exception;

public class NoLoginedUserException extends RuntimeException {
    public NoLoginedUserException(String message) {
        super(message);
    }
}
