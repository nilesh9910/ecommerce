package com.example.ecommerce.services;

import com.example.ecommerce.exceptions.UnauthourisedExcepiton;
import com.example.ecommerce.models.ESProduct;
import com.example.ecommerce.models.Product;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface IProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product)  throws UnauthourisedExcepiton;
    Product updateProduct(Product product)  throws UnauthourisedExcepiton;
    List<ESProduct> getProductByName(String name);
    List<Product> searchProduct(String search) throws IOException;
}
