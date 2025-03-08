package com.example.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseClass{
    String name;
    String description;
    @OneToMany(mappedBy = "category")
    List<Product> products;

    public Category() {
    }

    public Category(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
