package com.example.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    Boolean adminRequired;


    public Product() {
    }

    public Product(Long id, String name, String description, Double price) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(Long id, String name, String description, Double price, String imgUrl, Category category) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.category = category;
    }
}
