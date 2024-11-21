package com.triings.trringscommon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class MethodArgumentTypeMismatchException extends RuntimeException{

    private final ApiError apiError;
    public MethodArgumentTypeMismatchException(String code, String error) {
        super(error);
        this.apiError = new ApiError(code, HttpStatus.BAD_REQUEST, error);
    }
}
