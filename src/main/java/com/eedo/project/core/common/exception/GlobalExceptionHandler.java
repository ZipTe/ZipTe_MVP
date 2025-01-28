package com.eedo.project.core.common.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.core.common.CustomException;
import com.eedo.project.core.common.ErrorCode;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 이것은 어떤 예외인지는 모르겠다.
    @ExceptionHandler(value = {NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ApiResponse<?> handleNoPageFoundException(Exception e) {
        log.error("GlobalExceptionHandler catch : {}", e.getMessage());
        return ApiResponse.fail(new CustomException(ErrorCode.NOT_FOUND_END_POINT));
    }

    // 이것은 어떤 예외인지는 모르겠다.
    @ExceptionHandler(value = TypeMismatchException.class)
    public ApiResponse<?> handleMismatchException(Exception e) {
        log.error("GlobalExceptionHandler catch : {}", e.getMessage());
        return ApiResponse.fail(new CustomException(ErrorCode.BAD_REQUEST));
    }

    // NullPointException 일 때, 예외처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiResponse<?> handleNullPointException(NullPointerException e) {
        log.error("GlobalExceptionHandler catch : {}", e.getMessage());
        return ApiResponse.fail(new CustomException(ErrorCode.BAD_REQUEST));
    }

    // NoSuchElementException, EntityNotFoundException 일 때, 예외처리
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NoSuchElementException.class,EntityNotFoundException.class})
    public ApiResponse<?> handleNoSuchElementException(Exception e) {
        log.error("GlobalExceptionHandler catch : {}", e.getMessage());
        return ApiResponse.fail(new CustomException(ErrorCode.NOT_FOUND_END_POINT));
    }
}
