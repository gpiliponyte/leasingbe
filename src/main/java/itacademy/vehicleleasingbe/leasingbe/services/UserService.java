package itacademy.vehicleleasingbe.leasingbe.services;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.User;
import itacademy.vehicleleasingbe.leasingbe.beans.documents.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
