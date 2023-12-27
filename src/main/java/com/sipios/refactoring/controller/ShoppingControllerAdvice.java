package com.sipios.refactoring.controller;

import com.sipios.refactoring.exception.PriceIsTooHighException;
import com.sipios.refactoring.exception.UnknownCustomerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ShoppingControllerV2.class)
public class ShoppingControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingControllerAdvice.class);

    @ExceptionHandler({PriceIsTooHighException.class, UnknownCustomerType.class})
    public ResponseEntity<String> handle(RuntimeException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
