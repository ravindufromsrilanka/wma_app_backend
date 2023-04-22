package com.wma.wma.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileManageException extends Exception {
    private static final long serialVersionUID = 1L;


    private final HttpStatus status;
    private final String message;

    public FileManageException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public FileManageException(HttpStatus status, String message, Throwable exception) {
        super(exception);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
