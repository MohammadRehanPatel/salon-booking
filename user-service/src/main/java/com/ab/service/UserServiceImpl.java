package com.ab.service;

import com.ab.exception.UserException;
import com.ab.model.User;
import com.ab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            return  opt.get();
        }

        throw new UserException("user not found");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty()){
            throw new UserException("User not found With Id "+id);
        }
        userRepository.delete(opt.get());

    }

    @Override
    public User updateUser(long id, User user) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty()){
            throw new UserException("User not found With Id "+id);
        }
        User existingUser = opt.get();
        existingUser.setFullName(user.getFullName());
        existingUser.setPhone(user.getPhone());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setUsername(user.getUsername());
        return userRepository.save(existingUser);
    }
}
