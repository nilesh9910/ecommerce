package com.example.userAuthentication.service;

import com.example.userAuthentication.dtos.EmailDTO;
import com.example.userAuthentication.exceptions.UserAlreadyExistsException;
import com.example.userAuthentication.models.Role;
import com.example.userAuthentication.models.User;
import com.example.userAuthentication.repos.UserRepo;
import com.example.userAuthentication.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService{

    @Autowired
    UserRepo userRepo;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    ObjectMapper objectMapper;


    public User signUp(String email, String password)  throws  UserAlreadyExistsException{
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(optionalUser.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.getRoles().add(Role.USER);
            User savedUser = userRepo.save(newUser);
            try {
                EmailDTO emailDTO = new EmailDTO();
                emailDTO.setTo(email);
                emailDTO.setSubject("Welcome to Ecommerce");
                emailDTO.setBody("Happy to have you onboard");
                kafkaProducer.sendMessage("sendEmail", objectMapper.writeValueAsString(emailDTO));
            }
            catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
            return savedUser;
        }
        else throw new UserAlreadyExistsException("User Already exists. Please login");
    }
    public User signIn(String email, String password) throws RuntimeException{
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(optionalUser.isEmpty()) throw new RuntimeException("Incorrect User or Password");
        User user = optionalUser.get();
        if(passwordEncoder.matches(password, user.getPassword())) return user;
        else throw new RuntimeException("Incorrect User or Password");
    }

    public Boolean validateToken(String token) {
        try {
            String username = jwtUtil.extractUsername(token);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            return jwtUtil.validateToken(token, userDetails);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
