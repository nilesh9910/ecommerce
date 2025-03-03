package com.example.userAuthentication.service;

import com.example.userAuthentication.models.Role;
import com.example.userAuthentication.models.User;
import com.example.userAuthentication.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByEmail(username);
        if(optionalUser.isEmpty()) throw new UsernameNotFoundException("User not found: " + username);
        User user = optionalUser.get();
        List<String> roles  = new ArrayList<>();
        for(Role role: user.getRoles()) {
            roles.add(role.toString());
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
    }
}
