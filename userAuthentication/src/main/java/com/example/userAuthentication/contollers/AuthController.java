package com.example.userAuthentication.contollers;

import com.example.userAuthentication.dtos.SignUpRequestDTO;
import com.example.userAuthentication.dtos.UserDTO;
import com.example.userAuthentication.exceptions.UserAlreadyExistsException;
import com.example.userAuthentication.models.User;
import com.example.userAuthentication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping
    UserDTO signUP(SignUpRequestDTO requestDTO) throws UserAlreadyExistsException {
        User user = authService.signUp(requestDTO.getEmail(), requestDTO.getPassword());
        return from(user);
    }
    UserDTO from(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

}
