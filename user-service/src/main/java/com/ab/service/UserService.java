package com.ab.service;

import com.ab.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User getUserById(long id);
    List<User> getAllUsers();
    void deleteUser(long id);
    User updateUser(long id,User user);

}
