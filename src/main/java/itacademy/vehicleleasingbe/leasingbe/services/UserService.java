package itacademy.vehicleleasingbe.leasingbe.services;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.User;
import itacademy.vehicleleasingbe.leasingbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean getUser(String username){
        System.out.println(username);
        System.out.println(userRepository.findByUsername(username).getUsername());
        if (userRepository.findByUsername(username).getUsername().equals(username))
            return true;
        else
            return false;
    }

    public User addNewUser(@Valid User user){
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        return userRepository.save(newUser);
    }
}
