package com.anderson.trakd.service;

import com.anderson.trakd.model.User;
import com.anderson.trakd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(User user) throws Exception{
        User userExists = userRepository.findByEmail((user.getEmail()));
        if(userExists !=null){
            throw new Exception("Account with that email already exists.");
        }
        userRepository.save(user);
    }
}
