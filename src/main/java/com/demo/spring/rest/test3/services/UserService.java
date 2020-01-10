package com.demo.spring.rest.test3.services;

import com.demo.spring.rest.test3.models.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUserList();

    Optional<User> getUserById(Long id);
    List<User> getUserListByLastName(String lastName);

    void deleteUser(Long id);

    User saveUser(User user);
}
