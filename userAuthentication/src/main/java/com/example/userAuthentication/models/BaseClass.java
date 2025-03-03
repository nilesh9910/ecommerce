package com.example.userAuthentication.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @CreatedDate
    Date createdAt;
    @LastModifiedDate
    Date updatedAT;
}
