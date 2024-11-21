package com.triings.trringscommon.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private String code;
    @JsonIgnore
    private HttpStatus httpStatus;
    private String status;
    private List<String>errors;
    public ApiError(String code, HttpStatus httpStatus, String error){
        super();
        this.code=code;
        this.httpStatus=httpStatus;
        this.errors=List.of(error);
    }
}
