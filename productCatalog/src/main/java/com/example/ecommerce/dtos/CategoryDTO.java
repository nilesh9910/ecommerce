package com.example.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    Long id;
    String name;
    String description;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
