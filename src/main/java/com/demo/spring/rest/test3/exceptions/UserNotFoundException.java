package com.demo.spring.rest.test3.exceptions;

/**
 * Created by efrain.salomon on 1/9/2020.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
