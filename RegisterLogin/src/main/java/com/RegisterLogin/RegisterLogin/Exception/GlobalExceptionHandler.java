package com.RegisterLogin.RegisterLogin.Exception;

import com.RegisterLogin.RegisterLogin.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //hander runtime exception
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handingRuntimeException(RuntimeException exception){

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(1001);
        apiResponse.setMessage(exception.getMessage());

        //error base on user fault -> badrequest = 400
        // return a responseentity
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handingAppException(AppException exception){

        ErrorCode errorCode = exception.getErrorCode();

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        //error base on user fault -> badrequest = 400
        // return a responseentity
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<String> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return ResponseEntity.badRequest().body(exception.getFieldError().getDefaultMessage());
    }

}
