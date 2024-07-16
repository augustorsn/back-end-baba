package io.github.augustorsn.back_end_baba.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.augustorsn.back_end_baba.exception.RegraNegocioException;
import io.github.augustorsn.back_end_baba.rest.dto.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {


    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String msgErro = ex.getMessage();
        return new ApiErrors(msgErro);
    }
}