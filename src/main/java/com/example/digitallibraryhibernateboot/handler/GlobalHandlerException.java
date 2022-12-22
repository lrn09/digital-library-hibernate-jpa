package com.example.digitallibraryhibernateboot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalHandlerException {
    @ExceptionHandler(Exception.class)
    public void printStackTrace(Exception e) {
       e.printStackTrace();
    }
}

