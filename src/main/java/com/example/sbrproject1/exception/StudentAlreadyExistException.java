package com.example.sbrproject1.exception;

public class StudentAlreadyExistException extends RuntimeException {
    public StudentAlreadyExistException(String message)
    {
        super(message);
    }
}
