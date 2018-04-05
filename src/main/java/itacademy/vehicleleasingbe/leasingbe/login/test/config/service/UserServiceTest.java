package itacademy.vehicleleasingbe.leasingbe.login.test.config.service;

import itacademy.vehicleleasingbe.leasingbe.login.test.config.model.User;
import itacademy.vehicleleasingbe.leasingbe.login.test.config.model.UserDto;

import java.util.List;

public interface UserServiceTest {

    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
