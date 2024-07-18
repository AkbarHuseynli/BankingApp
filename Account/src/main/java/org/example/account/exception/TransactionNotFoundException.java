package org.example.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException {

    private ExceptionMessage exceptionMessage;

    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(ExceptionMessage message) {
        this.exceptionMessage = message;
    }

    public TransactionNotFoundException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}
