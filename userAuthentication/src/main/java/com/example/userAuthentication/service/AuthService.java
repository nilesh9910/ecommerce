package com.example.userAuthentication.service;

import com.example.userAuthentication.exceptions.UserAlreadyExistsException;
import com.example.userAuthentication.models.User;
import com.example.userAuthentication.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class AuthService{

    @Autowired
    UserRepo userRepo;
    public User signUp(String email, String password)  throws  UserAlreadyExistsException{
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(optionalUser.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setRoles(new HashSet<>());
            return newUser;
        }
        else throw new UserAlreadyExistsException("User Already exists. Please login");
    }
}
