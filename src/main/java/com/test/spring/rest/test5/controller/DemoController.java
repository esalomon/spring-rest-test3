package com.test.spring.rest.test5.controller;

import com.test.spring.rest.test5.model.ArrayModel;
import com.test.spring.rest.test5.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/test")
    public ArrayModel getMessage() {

        ArrayModel arrayModel = new ArrayModel();

        List<Integer> arrayOne = new ArrayList<>();
        List<Integer> arrayTwo = new ArrayList<>();

        arrayOne.add(5);
        arrayOne.add(4);
        arrayOne.add(6);

        arrayTwo.add(9);
        arrayTwo.add(8);
        arrayTwo.add(7);
        arrayTwo.add(6);

        arrayModel.setArrayOne(arrayOne);
        arrayModel.setArrayTwo(arrayTwo);
        //-----------------------
        //call the microservice to process the received data.
        demoService.orderArrays(arrayModel);

        //Return the requested ordered data.
        return arrayModel;
    }

    @PostMapping("/order")
    public List<Integer> getArrayOrdered(@RequestBody ArrayModel arrayModel){

        //Verify if the data is not null.
        //if (arrayModel == null)

        //call the microservice to process the received data.
        List<Integer> orderedList = demoService.orderArrays(arrayModel);

        //Return the requested ordered data.
        return orderedList;
    }
}
