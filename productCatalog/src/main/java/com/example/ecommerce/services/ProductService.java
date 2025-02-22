package com.example.ecommerce.services;

import com.example.ecommerce.models.Product;
import com.example.ecommerce.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class ProductService implements IProductService{

    @Autowired
    ProductRepo productRepo;
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }
}
