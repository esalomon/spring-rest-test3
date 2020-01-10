package com.demo.spring.rest.test3.services;

import com.demo.spring.rest.test3.exceptions.MethodParameterException;
import org.springframework.stereotype.Service;

/**
 * Created by efrain.salomon on 1/9/2020.
 */
@Service
public class CommonService {

    public <T> void validateInstance(String fieldName, T reference ) {

        if (reference == null) {

            throw new MethodParameterException("The parameter: '" + fieldName + "was null.");
        }
    }
}
