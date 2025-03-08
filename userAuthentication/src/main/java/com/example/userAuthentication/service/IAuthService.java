package com.example.userAuthentication.service;

import com.example.userAuthentication.exceptions.UserAlreadyExistsException;
import com.example.userAuthentication.models.User;

public interface IAuthService {
    public User signUp(String email, String password) throws UserAlreadyExistsException;
    public User signIn(String email, String password) throws Exception;
    public Boolean validateToken(String token);
}
