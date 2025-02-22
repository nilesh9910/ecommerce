package com.example.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseClass {
    String name;
    String description;
    Double price;
    String imgUrl;
    @ManyToOne
    Category category;
}
