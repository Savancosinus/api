package com.hardisgroup.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IOExceptionApi extends RuntimeException{
    HttpStatus code;
    String details;

    public IOExceptionApi(HttpStatus code, String details) {
        this.code = code;
        this.details = details;
    }
}
