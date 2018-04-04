package itacademy.vehicleleasingbe.leasingbe.controllers;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.User;
import itacademy.vehicleleasingbe.leasingbe.beans.response.UserResponse;
import itacademy.vehicleleasingbe.leasingbe.repositories.UserRepository;
import itacademy.vehicleleasingbe.leasingbe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public boolean findUser(@PathVariable("username") String username){
        return userService.getUser(username);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public UserResponse newUser(@Valid @RequestBody User user){
        return new UserResponse(userService.addNewUser(user));
    }

}
