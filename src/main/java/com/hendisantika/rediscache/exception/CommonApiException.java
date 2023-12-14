package com.hendisantika.rediscache.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/14/23
 * Time: 07:36
 * To change this template use File | Settings | File Templates.
 */
public class CommonApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
    private final Object errors;

    public CommonApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
        this.errors = null;
    }

    public CommonApiException(String message, HttpStatus status, Object errors) {
        super(message);
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getErrors() {
        return errors;
    }
}
