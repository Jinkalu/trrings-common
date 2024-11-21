package com.triings.trringscommon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BadRequestException extends RuntimeException {
    private final ApiError apiError;

    public BadRequestException(String code, String error){
        super(error);
        this.apiError = new ApiError(code, HttpStatus.BAD_REQUEST,error);
    }
}
