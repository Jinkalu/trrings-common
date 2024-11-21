package com.triings.trringscommon.exception;


import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


import static com.triings.trringscommon.exception.ErrorCode.BAD_CREDENTIALS;
import static com.triings.trringscommon.exception.ErrorCode.FORBIDDEN;
import static javax.management.remote.JMXConnectionNotification.FAILED;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError>handleValidationError(ValidationException e){
        ApiError errorRequest = e.getApiError();
        return new ResponseEntity<>(getErrorMap(errorRequest),new HttpHeaders(),errorRequest.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ApiError errorRequest = ApiError.builder().errors(errors)
                .code(HttpStatus.BAD_REQUEST.name())
                .status(FAILED)
                .httpStatus(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    private ApiError getErrorMap(ApiError apiErrorRequest){
        return ApiError.builder().code(apiErrorRequest.getCode())
                .status(apiErrorRequest.getHttpStatus().name())
                .errors(apiErrorRequest.getErrors()).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundError(NotFoundException e) {
        ApiError errorRequest = e.getApiError();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex) {
        ApiError errorRequest = ex.getApiError();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiError handleIllegalArgumentException(IllegalArgumentException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return ApiError.builder().errors(errors)
                .code("INVALID_INPUT_VALUE")
                .status(FAILED)
                .build();
    }


    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<ApiError> AwsInvalidImageFormatException(FileSizeLimitExceededException ex) {
        ApiError errorRequest = ApiError.builder()
                .errors(List.of("The File Exceeds Its Maximum Permitted Size Of  1 Mb"))
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .status(FAILED)
                .httpStatus(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiError bad_credentials = ApiError.builder().errors(errors)
                .code(BAD_CREDENTIALS)
                .status("BAD CREDENTIALS")
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .build();
        return new ResponseEntity<>(getErrorMap(bad_credentials), new HttpHeaders(), bad_credentials.getHttpStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiError error = ApiError.builder().errors(errors)
                .code(FORBIDDEN)
                .status(HttpStatus.FORBIDDEN.name())
                .httpStatus(HttpStatus.FORBIDDEN)
                .build();
        return new ResponseEntity<>(getErrorMap(error), new HttpHeaders(), error.getHttpStatus());
    }


}
