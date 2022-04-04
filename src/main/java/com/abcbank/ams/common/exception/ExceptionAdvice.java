package com.abcbank.ams.common.exception;

import com.abcbank.ams.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    public static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(AmsException.class)
    public ResponseEntity<ErrorResponse> handleAmsExceptions(AmsException ex) {
        log.error("Ams Exception - Message : {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(ex.getErrorMessage());
        errorResponse.setErrorCode(ex.getErrorCode());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        log.error("Server Error - Message : {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setErrorCode("ERR" + HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
