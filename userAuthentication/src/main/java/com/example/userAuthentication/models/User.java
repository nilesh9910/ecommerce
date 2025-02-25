package com.example.userAuthentication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseClass{
    String email;
    String password;
    @Enumerated(value = EnumType.ORDINAL)
    Set<Role> roles;
}
