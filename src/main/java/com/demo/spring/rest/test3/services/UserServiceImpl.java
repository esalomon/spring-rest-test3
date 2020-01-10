package com.demo.spring.rest.test3.services;

import com.demo.spring.rest.test3.models.User;
import com.demo.spring.rest.test3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by efrain.salomon on 1/9/2020.
 */
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUserList() {

        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);
    }

    @Override
    public List<User> getUserListByLastName(String lastName) {

        return userRepository.findAllByLastName(lastName);
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    @Override
    public User saveUser(User user) {

        return userRepository.save(user);
    }
}
