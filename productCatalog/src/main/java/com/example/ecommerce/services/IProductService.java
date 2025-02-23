package com.example.ecommerce.services;

import com.example.ecommerce.models.ESProduct;
import com.example.ecommerce.models.Product;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface IProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(Product product);
    List<ESProduct> getProductByName(String name);
    List<Product> searchProduct(String search) throws IOException;
}
