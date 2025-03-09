package com.example.ecommerce.client;

import com.example.ecommerce.dtos.TokenDTO;
import com.example.ecommerce.models.Role;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserServiceClient {

    RestTemplate restTemplate;
    RedisTemplate<String, Object> redisTemplate;
    ObjectMapper objectMapper;
    UserServiceClient(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }
    public List<Role> getRoles(String token, String userId) {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        Role[] jsonRoles = (Role[]) redisTemplate.opsForValue().get("User_" + userId);
        if(jsonRoles!=null) {
            System.out.println("Reading from Cache");
            return new ArrayList<>(Arrays.asList(jsonRoles));
        }
//        try {
//            if (jsonRoles instanceof List<?>) {
//                return objectMapper.convertValue(jsonRoles, new TypeReference<List<Role>>() {});
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        System.out.println("Reading for api");
        Role[] roles = restTemplate.postForObject( "http://localhost:8081/auth/getRoles", tokenDTO, Role[].class);

        if(roles==null) return new ArrayList<>();
        redisTemplate.opsForValue().set("User_"+userId, roles);
        return new ArrayList<>(Arrays.asList(roles));
    }
}
