package com.triings.trringscommon.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO <T> implements Serializable {


    @Serial
    private static final long serialVersionUID = -3154463786423785136L;

    private String code;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    public ResponseVO(String code,String status, String message){
        this.code=code;
        this.status=status;
        this.message=message;
    }
    public ResponseVO(String code, String status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }
}
