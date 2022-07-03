package com.rishabh.expensetracker.exception;

import com.rishabh.expensetracker.entity.ErrorObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException e, WebRequest request){

        ErrorObject enew = new ErrorObject();

        enew.setStatusCode(HttpStatus.NOT_FOUND.value());
        enew.setMessage(e.getMessage());
        enew.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(enew, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest request){

        ErrorObject enew = new ErrorObject();

        enew.setStatusCode(HttpStatus.BAD_REQUEST.value());
        enew.setMessage(e.getMessage());
        enew.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(enew, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception e, WebRequest request){

        ErrorObject enew = new ErrorObject();

        enew.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        enew.setMessage(e.getMessage());
        enew.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(enew, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("timestamp",new Date());
        map.put("status code",HttpStatus.BAD_REQUEST);
        map.put("message",ex.getBindingResult().getFieldErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.toList()));

        return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
    }
}
