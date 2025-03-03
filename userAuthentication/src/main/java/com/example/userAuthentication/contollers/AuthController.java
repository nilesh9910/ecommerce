package com.example.userAuthentication.contollers;

import com.example.userAuthentication.dtos.SignInRequestDTO;
import com.example.userAuthentication.dtos.SignUpRequestDTO;
import com.example.userAuthentication.dtos.TokenDTO;
import com.example.userAuthentication.dtos.UserDTO;
import com.example.userAuthentication.exceptions.IncorrectUserNameOrPasswordException;
import com.example.userAuthentication.exceptions.UserAlreadyExistsException;
import com.example.userAuthentication.models.User;
import com.example.userAuthentication.service.IAuthService;
import com.example.userAuthentication.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAuthService authService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signup")
    ResponseEntity<UserDTO> signUP(@RequestBody SignUpRequestDTO requestDTO) throws UserAlreadyExistsException {
        User user = authService.signUp(requestDTO.getEmail(), requestDTO.getPassword());
        return new ResponseEntity<>(from(user), HttpStatus.OK);
    }

    @PostMapping("/signin")
    ResponseEntity<UserDTO> signIn(@RequestBody SignInRequestDTO signInRequestDTO) throws Exception {
        User user = authService.signIn(signInRequestDTO.getEmail(), signInRequestDTO.getPassword());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        ResponseCookie responseCookie = ResponseCookie.from("auth_token", jwtUtil.generateToken(user.getEmail()))
                .maxAge(60 * 60)
                .build();
        headers.add(HttpHeaders.SET_COOKIE, responseCookie.toString());
        return new ResponseEntity<>(from(user), headers, HttpStatus.OK);
    }
    @PostMapping("/validateToken")
    Boolean validateToken(@RequestBody TokenDTO tokenDTO) {
        return authService.validateToken(tokenDTO.getToken());
    }
    UserDTO from(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

}
