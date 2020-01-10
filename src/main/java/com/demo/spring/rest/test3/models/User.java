package com.demo.spring.rest.test3.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by efrain.salomon on 1/9/2020.
 */
@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long Id;

    @NotNull
    @Size(min=2, message="The first name should have at list 2 characters")
    private String firstName;

    @Size(min=3, message="The last name should have at list 3 characters")
    private String lastName;

    //------------------------------------------------------------------------------------------------
    public User(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName  = lastName;
    }
}
