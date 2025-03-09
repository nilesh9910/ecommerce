package com.example.userAuthentication.service;

import com.example.userAuthentication.exceptions.UserAlreadyExistsException;
import com.example.userAuthentication.models.Role;
import com.example.userAuthentication.models.User;

import java.util.List;

public interface IAuthService {
    public User signUp(String email, String password) throws UserAlreadyExistsException;
    public User signIn(String email, String password) throws Exception;
    public Boolean validateToken(String token);
    public List<Role> getRoles(String token);
}
