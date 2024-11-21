package com.triings.trringscommon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException{
    private final ApiError apiError;

    public ValidationException(String code, HttpStatus httpStatus, String error){
        super(error);
        this.apiError=new ApiError(code,httpStatus,error);
    }

}
