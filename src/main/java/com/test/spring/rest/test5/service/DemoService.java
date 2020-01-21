package com.test.spring.rest.test5.service;

import com.test.spring.rest.test5.model.ArrayModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoService {

    public List<Integer> orderArrays(ArrayModel arrayModel) {

        //Defines the store for the two list
        List<Integer> arrayOne = arrayModel.getArrayOne();
        List<Integer> arrayTwo = arrayModel.getArrayTwo();

        //Defines a store for the merged lists.
        List<Integer> mergedArray = new ArrayList<>();

        //stores the original lists inside the third one.
        mergedArray.addAll(arrayOne);
        mergedArray.addAll(arrayTwo);

        //order the the merged list.
        //Collections.sort(mergedArray, Collections.reverseOrder());

        //Returns the requested data.
        //return mergedArray;

        //----------------------------------
        //Natural order using streams.
        /*List<Integer> arrayOneOrdered =
                arrayOne.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());*/

        //Reverse order using streams.
        return mergedArray.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
