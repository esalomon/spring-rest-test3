package com.demo.spring.rest.test3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by efrain.salomon on 1/9/2020.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullParamException extends RuntimeException {

    public NullParamException(String message) {
        super(message);
    }
}
