package com.bot.exception.handlers;

import com.bot.exception.TypeBranchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class BranchNotFoundExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({NoSuchElementException.class})
    protected ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Specialties not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler({TypeBranchException.class})
    protected ResponseEntity<Object> typeNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "There is no such type", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
