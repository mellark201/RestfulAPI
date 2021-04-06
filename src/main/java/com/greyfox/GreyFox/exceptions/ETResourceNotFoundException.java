package com.greyfox.GreyFox.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ETResourceNotFoundException extends RuntimeException{
    public ETResourceNotFoundException(String message) {
        super(message);
    }
}
