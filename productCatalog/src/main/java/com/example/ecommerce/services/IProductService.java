package com.example.ecommerce.services;

import com.example.ecommerce.models.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductService {
    Product createProduct(Product product);
}
