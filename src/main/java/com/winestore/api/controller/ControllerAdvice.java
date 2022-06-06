package com.winestore.api.controller;

import com.winestore.api.dto.ErrorDTO;
import com.winestore.exception.WrongPasswordException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;

@RestControllerAdvice
public class ControllerAdvice {

    private static final int WRONG_PASSWORD = 1;
    private static final int NOT_FOUND = 2;
    private static final int BLOCKED = 3;
    private static final int BANED = 4;
    private static final int ALREADY_EXISTS = 5;

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidParameterException.class)
    public ErrorDTO handleInvalidParameter(InvalidParameterException ex) {
        ErrorDTO error = new ErrorDTO();
        error.setMessage(ex.getMessage());

        if (ex.getMessage().equals("Password doesn't correct")) {
            error.setErrorCode(WRONG_PASSWORD);
        }

        return error;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorDTO handleInvalidParameter(EntityNotFoundException ex) {
        ErrorDTO error = new ErrorDTO();
        error.setMessage(ex.getMessage());
        error.setErrorCode(NOT_FOUND);

        return error;
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorDTO handleInvalidParameter(AccessDeniedException ex) {
        ErrorDTO error = new ErrorDTO();
        error.setErrorCode(NOT_FOUND);

        if (ex.getMessage().equals("User is blocked")) {
            error.setErrorCode(BLOCKED);
        } else {
            error.setErrorCode(BANED);
        }

        return error;
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorDTO handleAlreadyExistsException(DataIntegrityViolationException ex) {
        ErrorDTO error = new ErrorDTO();
        error.setErrorCode(ALREADY_EXISTS);
        error.setMessage("Entity already exists in DB");

        return error;
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(WrongPasswordException.class)
    public ErrorDTO handleWrongPasswordException(WrongPasswordException ex) {
        ErrorDTO error = new ErrorDTO();
        error.setMessage(ex.getMessage());
        error.setErrorCode(1);

        return error;
    }
}
