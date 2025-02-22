package com.example.ecommerce.dtos;

import com.example.ecommerce.models.Category;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    Long id;
    String name;
    String description;
    Double price;
    String imgUrl;
    CategoryDTO category;
}