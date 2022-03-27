package com.example.modul2_theme1_task2.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse implements Serializable {
    private String message;
    private boolean success;
    private Object data;
    private HttpStatus httpStatusCode = HttpStatus.OK;

    public ApiResponse(String message, boolean success, HttpStatus httpStatusCode) {
        this.message = message;
        this.success = success;
        this.httpStatusCode = httpStatusCode;
    }

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

}