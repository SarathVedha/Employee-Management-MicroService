package com.vedha.organization.exception;

import com.vedha.organization.dto.ErrorMessageDTO;
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
public class GlobalExceptions {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDTO> handleGlobalExceptions(Exception exception, WebRequest request) {

        ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.builder()
                .errorCode("ERR00")
                .errorMessage(exception.getMessage())
                .timeStamp(LocalDateTime.now())
                .uriPath(request.getDescription(true)).build();

        return new ResponseEntity<>(errorMessageDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrganizationException.class)
    public ResponseEntity<ErrorMessageDTO> handleOrganizationExceptions(OrganizationException exception, WebRequest webRequest) {

        ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.builder()
                .errorCode("ERR01")
                .errorMessage(exception.getMessage())
                .timeStamp(LocalDateTime.now())
                .uriPath(webRequest.getDescription(true)).build();

        return new ResponseEntity<>(errorMessageDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception, WebRequest webRequest) {

        HashMap<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(objectError -> {
            String field = ((FieldError) objectError).getField();
            String defaultMessage = objectError.getDefaultMessage();

            errors.put(field, defaultMessage);

        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
