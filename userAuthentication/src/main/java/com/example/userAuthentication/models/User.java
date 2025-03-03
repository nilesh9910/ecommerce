package com.example.userAuthentication.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseClass{
    String email;
    String password;
    @ElementCollection
    @Enumerated(value = EnumType.ORDINAL)
    Set<Role> roles = new HashSet<>();
}
