package com.netcracker.store.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
public class ResponseInputException extends Exception {

    public ResponseInputException(String message) {
        super(message);
    }
}
