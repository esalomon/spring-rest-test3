package com.test.spring.rest.test5.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
public class ArrayModel {

    private List<Integer> arrayOne;
    private List<Integer> arrayTwo;
}
