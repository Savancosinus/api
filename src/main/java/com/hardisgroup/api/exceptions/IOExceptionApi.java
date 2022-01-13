package com.hardisgroup.api.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * IOExceptionApi.
 * Levée en cas de lecture d'un fichier invalide (fichier absent ou donénes corrompues).
 */
@Getter
public class IOExceptionApi extends RuntimeException {
    HttpStatus code;
    String details;

    public IOExceptionApi(HttpStatus code, String details) {
        this.code = code;
        this.details = details;
    }

    // Constructeur avec message (ex: IOException)
    public IOExceptionApi(HttpStatus code, String details, String message) {
        super(message);
        this.code = code;
        this.details = details;
    }
}
