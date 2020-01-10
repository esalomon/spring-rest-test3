package com.demo.spring.rest.test3.controllers;

import com.demo.spring.rest.test3.exceptions.RequestParameterException;
import com.demo.spring.rest.test3.exceptions.UserNotFoundException;
import com.demo.spring.rest.test3.models.User;
import com.demo.spring.rest.test3.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.demo.spring.rest.test3.common.AppConstants.ID;
import static com.demo.spring.rest.test3.common.AppConstants.USERS_V2;

/**
 * Created by efrain.salomon on 1/9/2020.
 */
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/v1" + "/users")
    public List<User> getUserList() {

        logger.info("The getUserList()'s v1 was called.");

        List<User> userList = new ArrayList<>();
        userList.add(new User("John1", "Smith1"));
        userList.add(new User("Joe1",  "Jones1"));
        userList.add(new User("Jone1", "Brown1"));

        return userList;
    }

    @GetMapping(USERS_V2) //"/v2" + "/users"
    public ResponseEntity<List<User>> getUserList2(@RequestParam(required = false)
                                                   String lastName) {

        //If the parameter is null retrieves all the users.
        if (lastName == null) {

            List<User> userList = userService.getUserList();
            return new ResponseEntity<>(userList, HttpStatus.OK);

        } else {

            //Filters the list using the last name.
            return new ResponseEntity<>(userService.getUserListByLastName(lastName),
                                        HttpStatus.OK);
        }
    }

    //@GetMapping(path = USERS_V2 + ID,
    //            produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @GetMapping(USERS_V2 + ID)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        Optional<User> optionalUser = userService.getUserById(id);
        if (!optionalUser.isPresent()) throw new UserNotFoundException("User with Id: " + id);
        return new ResponseEntity<>(optionalUser.get(),
                                    HttpStatus.OK);
    }

    @GetMapping(USERS_V2 + "ByLastName/{lastName}") //for some reason returns empty list.
    public ResponseEntity<List<User>> getUserByLastName(@PathVariable String lastName) {

        if (lastName == null) throw new RequestParameterException("The request parameter lastName was not provided.");

        //Filters the list using the last name.
        return new ResponseEntity<>(userService.getUserListByLastName(lastName),
                                    HttpStatus.OK);
    }

    @DeleteMapping(USERS_V2 + ID)
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = USERS_V2,
                 consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

        User userSaved = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path(ID)
            .buildAndExpand(userSaved.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(USERS_V2 + ID)
    public ResponseEntity<Object> updateUser(@PathVariable Long id,
                                             @Valid @RequestBody User user) {

        Optional<User> optionalUser = userService.getUserById(id);
        if (!optionalUser.isPresent()) throw new UserNotFoundException("User with Id: " + id);

        final User userRetrieved = optionalUser.get();
        userRetrieved.setFirstName(user.getFirstName());
        userRetrieved.setLastName(user.getLastName());

        userService.saveUser(user);

        return ResponseEntity.noContent().build();
    }
}
