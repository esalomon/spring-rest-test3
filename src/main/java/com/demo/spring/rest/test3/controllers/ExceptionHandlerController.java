package com.demo.spring.rest.test3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;

/**
 * Created by efrain.salomon on 1/9/2020.
 */
@RestController
public class ExceptionHandlerController {

    @GetMapping("/run-time-exception")
    public void throwRunTimeException() {

        throw new RuntimeException("There was an issue returning the data.");
    }

    @GetMapping("/null-pointer-exception")
    public void throwNullPointerException() {

        throw new NullPointerException("There was an issue handling the data");
    }
}
