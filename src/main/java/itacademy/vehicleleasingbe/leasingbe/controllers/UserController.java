package itacademy.vehicleleasingbe.leasingbe.controllers;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.User;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.UserDto;
import itacademy.vehicleleasingbe.leasingbe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }



    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }



}
