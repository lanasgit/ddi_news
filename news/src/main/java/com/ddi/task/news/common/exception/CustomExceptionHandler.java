package com.ddi.task.news.common.exception;

import com.ddi.task.news.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<Object> handleCustomException(CustomException e) {
        log.error("CustomException -> {}", "{}", e.getMessage(), e);
        return new ResponseEntity<>(Response.error(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        log.error("Exception -> {}", "{}", e.getMessage(), e);
        return new ResponseEntity<>(Response.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
