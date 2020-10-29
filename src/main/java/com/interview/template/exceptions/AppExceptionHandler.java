package com.interview.template.exceptions;

import com.interview.template.model.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {

        return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> userNotFoundException(Exception ex, WebRequest request, HttpServletRequest httpRequest) {

        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.NOT_FOUND.value(),
                ex.getLocalizedMessage(), ex.toString(), httpRequest.getRequestURI());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {AlreadyExistsException.class})
    public ResponseEntity<Object> alreadyExist(Exception ex, WebRequest request, HttpServletRequest httpRequest) {

        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.CONFLICT.value(),
                ex.getLocalizedMessage(), ex.toString(), httpRequest.getRequestURI());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {UsernameNotAllowed.class})
    public ResponseEntity<Object> usernameNotAllowed(Exception ex, WebRequest request, HttpServletRequest httpRequest) {

        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.CONFLICT.value(),
                ex.getLocalizedMessage(), ex.toString(), httpRequest.getRequestURI());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.CONFLICT);
    }


}


