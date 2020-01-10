package com.demo.spring.rest.test3.repositories;

import com.demo.spring.rest.test3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by efrain.salomon on 1/9/2020.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByLastName(String lastName);
}
