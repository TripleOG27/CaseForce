package com.detelin.caseforce.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,reason = "query did not return a unique result")
public class NonUniqueResultError extends RuntimeException {
private int statusCode;
    public NonUniqueResultError() {
        this.statusCode = 409;
    }

    public NonUniqueResultError(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
