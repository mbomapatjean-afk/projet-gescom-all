package com.gescom.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotClientException extends RuntimeException {
    public NotClientException(String message) {
        super(message);
    }
}