package com.demo.spring.rest.test3.exceptions;

/**
 * Created by efrain.salomon on 1/9/2020.
 */
public class RequestParameterException extends RuntimeException {

    public RequestParameterException(String message) {

        super(message);
    }
}
