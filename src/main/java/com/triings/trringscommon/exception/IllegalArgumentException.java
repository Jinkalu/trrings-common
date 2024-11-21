package com.triings.trringscommon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class IllegalArgumentException extends RuntimeException{
    private final ApiError apiError;

    public IllegalArgumentException(String code, String error) {
        super(error);
        this.apiError = new ApiError(code, HttpStatus.BAD_REQUEST, error);
    }
}
