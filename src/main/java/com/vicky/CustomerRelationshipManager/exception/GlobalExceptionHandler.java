package com.vicky.CustomerRelationshipManager.exception;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        HashMap<String,String> mapError=new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldError= ((FieldError) error ).getField();
            String message= error.getDefaultMessage();
            mapError.put(fieldError,message);
        });
        return mapError;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String,String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        HashMap<String,String> response=new HashMap<>();
        response.put("error","Invalid date format. Please use YYYY-MM-DD format");
        //response.put("details :- ",e.getMessage());
        return response;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String,String> handleRuntimeException(RuntimeException e){
        HashMap<String,String> mapError=new HashMap<>();
        mapError.put("error",e.getMessage());
        return mapError;
    }
}
