package com.restapi.locationchecker.controller;


import com.restapi.locationchecker.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.bind.ValidationException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    ErrorMessage exceptionHandler(ValidationException e) {
        return new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST.toString());
    }
}
