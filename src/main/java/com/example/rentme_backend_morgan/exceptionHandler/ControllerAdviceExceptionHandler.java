package com.example.rentme_backend_morgan.exceptionHandler;


import com.example.rentme_backend_morgan.security.services.BadRequestException;
//import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
//import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

//@ControllerAdvice
public class ControllerAdviceExceptionHandler extends RuntimeException {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity exceptionHandler(RuntimeException ex, HttpServletRequest request) {
        String payload = "path: " + request.getRequestURI() + "; msg: " + ex.getMessage();
        return new ResponseEntity(payload, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity exceptionHandlerValid(MethodArgumentNotValidException ex) {
        return new ResponseEntity(ex.getFieldError().getField() + " is does not match requirements ", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity exceptionHandlerValid(ConstraintViolationException ex) {
        return new ResponseEntity(ex.getLocalizedMessage() + " is does not match requirements ", HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler({SQLException.class})
//    public ResponseEntity exceptionHandlerValid(PSQLException ex) {
//        String[] err = ex.getMessage().split("Detail:");
//        return new ResponseEntity(err[1], HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler({MysqlDataTruncation.class})
//    public ResponseEntity exceptionHandlerSQL(MysqlDataTruncation ex) {
//        String[] err = ex.getMessage().split("Detail:");
//        return new ResponseEntity(err[1], HttpStatus.BAD_REQUEST);
//    }

}
