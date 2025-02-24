package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.CategoryDTO;
import com.example.ecommerce.dtos.ProductDTO;
import com.example.ecommerce.exceptions.ProductNotFoundException;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.models.ESProduct;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    IProductService productService;
    @GetMapping
    List<ProductDTO> getAllProduct() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOS= new ArrayList<>();
        for(Product product: products) {
            productDTOS.add(from(product));
        }
        return  productDTOS;
    }

    @PutMapping
    ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        Product product = from(productDTO);
        Product updatedProduct = productService.updateProduct(product);
        if(updatedProduct==null) {
            throw new ProductNotFoundException("Product with product_id " + product.getId() + " does not exist");
        }
        return  from(updatedProduct);
    }

    @PostMapping
    ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        Product product = from(productDTO);
        Product response = productService.createProduct(product);
        return from(response);
    }
    Product from(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
        if(productDTO.getCategory()!=null) {
            Category category = new Category();
            category.setName(productDTO.getCategory().getName());
            category.setId(productDTO.getCategory().getId());
            category.setDescription(productDTO.getCategory().getDescription());
            product.setCategory(category);
        }
        return product;
    }
    ProductDTO from(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImgUrl(product.getImgUrl());
        if(product.getCategory()!=null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setDescription(product.getCategory().getDescription());
            categoryDTO.setName(product.getCategory().getName());
            categoryDTO.setId(product.getCategory().getId());
            productDTO.setCategory(categoryDTO);
        }
        return productDTO;
    }
    @GetMapping("{name}")
    List<ESProduct> getProductFromName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @GetMapping("search/{search}")
    List<ProductDTO> searchProduct(@PathVariable String search) throws IOException {
        List<Product> products =  productService.searchProduct(search);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product: products) {
            productDTOS.add(from(product));
        }
        return productDTOS;
    }
}
