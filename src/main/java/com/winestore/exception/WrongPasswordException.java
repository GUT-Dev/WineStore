package com.winestore.exception;

import java.io.Serial;

public class WrongPasswordException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5362572283376180416L;

    public WrongPasswordException(String message) {
        super(message);
    }
}
