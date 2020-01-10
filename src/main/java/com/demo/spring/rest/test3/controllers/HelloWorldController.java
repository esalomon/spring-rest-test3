package com.demo.spring.rest.test3.controllers;

import com.demo.spring.rest.test3.exceptions.ExceptionResponse;
import com.demo.spring.rest.test3.exceptions.NullParamException;
import com.demo.spring.rest.test3.services.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by efrain.salomon on 1/9/2020.
 */
@RestController
public class HelloWorldController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    CommonService commonService;

    @Autowired
    public HelloWorldController(CommonService commonService) {

        this.commonService = commonService;
    }

    @GetMapping("/hello-world")
    public String showHelloWorld() {

        logger.info("The showHelloWorld() method was called.");
        return "Hello World from a rest controller, " + LocalDateTime.now();
    }

    @GetMapping("hello-world2")
    public String showHelloWorld2(@RequestParam String name) {

        logger.info("The showHelloWorld2() method was called.");

        if (name == null) throw new NullParamException("The name query param was null");

        return "Hello '" + name + "' from a rest controller, " + LocalDateTime.now();
    }

    @GetMapping("hello-world3")
    public void showHelloWorld3() {

        logger.info("The showHelloWorld3() method was called.");

        throw new NullPointerException("There was a null variable.");
    }

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<ExceptionResponse> handleNullPointerException (NullPointerException exception) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        exceptionResponse.setCode(httpStatus.value());

        exceptionResponse.setTimestamp(LocalDateTime.now());
        exceptionResponse.setMessage("There was a null pointer exception calling the helloWorldController.");
        exceptionResponse.setDetails(exception.getMessage() + " StackTrace: " + getStackTraceFirstElement(exception));

        return new ResponseEntity<>(exceptionResponse, httpStatus);
    }

    private String getStackTraceFirstElement(NullPointerException exception) {

        commonService.validateInstance("exception", exception);

        String result = "There was no stack trace information";

        final StackTraceElement[] stackTraceArray = exception.getStackTrace();

        if (stackTraceArray.length > 0) {

            result = stackTraceArray[0].toString();
        }

        return result;
    }
}
