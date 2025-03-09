package com.example.ecommerce.services;

import com.example.ecommerce.client.UserServiceClient;
import com.example.ecommerce.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserServiceClient userServiceClient;
    UserService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }
    public List<String> getRoles(String token, String userId) {
        List<Role> roles = userServiceClient.getRoles(token, userId);
        return roles.stream().map(Enum::toString).collect(Collectors.toList());
    }
    public List<String> getRolesFromContext() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

    }
}
