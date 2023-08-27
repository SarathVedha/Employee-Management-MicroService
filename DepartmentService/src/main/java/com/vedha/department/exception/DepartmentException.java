package com.vedha.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DepartmentException extends RuntimeException{

    public DepartmentException(String message) {
        super(message);
    }
}
