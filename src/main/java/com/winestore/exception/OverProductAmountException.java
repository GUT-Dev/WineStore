package com.winestore.exception;

import java.io.Serial;

public class OverProductAmountException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2349036114995591638L;

    public OverProductAmountException(String message) {
        super(message);
    }
}
