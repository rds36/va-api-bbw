package com.rds.vaapibbw.controller;

import com.rds.vaapibbw.error.UserExistException;
import com.rds.vaapibbw.error.UserNotFoundException;
import com.rds.vaapibbw.error.VirtualAccountNotFoundException;
import com.rds.vaapibbw.model.WebResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public WebResponse<String> badRequestHandler(){
        WebResponse response = new WebResponse<>(
                "006",
                "GENERAL ERROR",
                "Bad Request"
        );
        return response;
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public WebResponse<String> userNotExistHandler(){
        WebResponse response = new WebResponse<>(
                "005",
                "CLIENT NOT FOUND",
                "Client ID not match or not found"
        );
        return response;
    }

    @ExceptionHandler(value = {VirtualAccountNotFoundException.class})
    public WebResponse<String> internalErrorHandler(){
        WebResponse response = new WebResponse<>(
                "003",
                "NOT FOUND",
                "VA not found"
        );
        return response;
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public WebResponse<String> dataIntegrityErrorHandler(){
        WebResponse response = new WebResponse<>(
                "006",
                "GENERAL ERROR",
                "Conflict data"
        );
        return response;
    }

    @ExceptionHandler(value = {UserExistException.class})
    public WebResponse<String> duplicateUserHandler(){
        WebResponse response = new WebResponse<>(
                "006",
                "GENERAL ERROR",
                "User exists"
        );
        return response;
    }
}
