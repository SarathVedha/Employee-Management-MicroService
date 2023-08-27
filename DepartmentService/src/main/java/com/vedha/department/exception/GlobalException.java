package com.vedha.department.exception;

import com.vedha.department.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
public class GlobalException { //ResponseEntityExceptionHandler

    @ExceptionHandler(DepartmentException.class)
    public ResponseEntity<ErrorMessageDTO> handleDepartmentException(DepartmentException departmentException, WebRequest webRequest) {

        ErrorMessageDTO err01 = ErrorMessageDTO.builder().errorCode("ERR01")
                .errorMessage(departmentException.getMessage())
                .timeStamp(LocalDateTime.now())
                .uriPath(webRequest.getDescription(true)).build();

        return new ResponseEntity<>(err01, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDTO> handleGlobalException(Exception exception, WebRequest webRequest) {

        ErrorMessageDTO err00 = ErrorMessageDTO.builder().errorCode("ERR00")
                .errorMessage(exception.getMessage())
                .timeStamp(LocalDateTime.now())
                .uriPath(webRequest.getDescription(true)).build();

        return new ResponseEntity<>(err00, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException exception) {

        HashMap<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(objectError -> {

            String field = ((FieldError) objectError).getField();
            String defaultMessage = objectError.getDefaultMessage();

            errors.put(field, defaultMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
