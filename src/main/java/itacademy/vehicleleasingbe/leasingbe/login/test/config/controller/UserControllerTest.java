package itacademy.vehicleleasingbe.leasingbe.login.test.config.controller;

import itacademy.vehicleleasingbe.leasingbe.login.test.config.model.User;
import itacademy.vehicleleasingbe.leasingbe.login.test.config.model.UserDto;
import itacademy.vehicleleasingbe.leasingbe.login.test.config.service.UserServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserControllerTest {

    @Autowired
    private UserServiceTest userServiceTest;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userServiceTest.findAll();
    }

//    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
//    public User getOne(@PathVariable(value = "id") Long id){
//        return userServiceTest.findById(id);
//    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        return userServiceTest.save(user);
    }



}
