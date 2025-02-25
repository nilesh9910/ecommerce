package com.example.userAuthentication.models;


import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseClass {
    Long id;
    @CreatedDate
    Date createdAt;
    @LastModifiedDate
    Date updatedAT;
}
