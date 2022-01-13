package com.hardisgroup.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * DTO "ExceptionResponse".
 * DTO représentant une exception levée par l'application.
 */
@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    HttpStatus code;
    String details;


}
