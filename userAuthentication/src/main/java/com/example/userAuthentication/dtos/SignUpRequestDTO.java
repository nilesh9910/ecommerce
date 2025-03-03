package com.example.userAuthentication.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    String email;
    String password;
}
