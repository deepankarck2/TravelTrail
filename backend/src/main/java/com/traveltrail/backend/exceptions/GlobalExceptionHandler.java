package com.traveltrail.backend.exceptions;

import com.traveltrail.backend.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(RuntimeException ex, WebRequest request) {
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .timestamp(System.currentTimeMillis())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex, WebRequest request) {

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnprocessiableException.class)
    public ResponseEntity<ErrorResponseDto> handleUnprocessibleException(Exception ex){
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .timestamp(System.currentTimeMillis()).build();

        return new ResponseEntity<>(errorResponseDto, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> illegalArgumentExceptionHanlder(Exception ex){
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(System.currentTimeMillis())
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponseDto> SecurityException(Exception ex){
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder().message(ex.getMessage())
                .statusCode(HttpStatus.FORBIDDEN.value()).timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorResponseDto, HttpStatus.FORBIDDEN);
    }
}
