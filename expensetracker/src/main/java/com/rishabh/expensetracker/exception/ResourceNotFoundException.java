package com.rishabh.expensetracker.exception;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String messsage) {
        super(messsage);
    }
}
