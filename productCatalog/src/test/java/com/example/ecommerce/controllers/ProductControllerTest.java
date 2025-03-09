package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.CategoryDTO;
import com.example.ecommerce.dtos.ProductDTO;
import com.example.ecommerce.exceptions.ProductNotFoundException;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.models.ESProduct;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.repos.ESProductRepo;
import com.example.ecommerce.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockitoBean
    private IProductService productService;

    @MockitoBean
    private ESProductRepo esProductRepo;


//    @Test
//    void testGetAllProduct() {
        //Acquire
//        List<Product> products = new ArrayList<>();
//        products.add(new Product(1L, "iphone", "desc", 100.0));
//        products.add(new Product(2L, "Samsung", "desc", 200.0));
//        List<ProductDTO> productDTOS = new ArrayList<>();
//        productDTOS.add(new ProductDTO(1L, "iphone", "desc", 100.0));
//        productDTOS.add(new ProductDTO(2L, "Samsung", "desc", 200.0));
//
//        when(productService.getAllProducts()).thenReturn(products);
//        when(esProductRepo.save(), )
//
//        //Act
//        List<ProductDTO> actualResult = productController.getAllProduct();
//        //Assert
//        assertEquals(productDTOS, actualResult);

//    }

    @Test
    void updateProduct_Success() {
        // Arrange
        ProductDTO productDTO = new ProductDTO(1L, "UpdatedProduct", "UpdatedDescription", 15.0, "updatedUrl",
                new CategoryDTO(1L, "Category1", "Desc1"));
        Product updatedProduct = new Product(1L, "UpdatedProduct", "UpdatedDescription", 15.0, "updatedUrl",
                new Category(1L, "Category1", "Desc1"));
        when(productService.updateProduct(any(Product.class))).thenReturn(updatedProduct);

        // Act
        ProductDTO result = productController.updateProduct(productDTO);

        // Assert
        assertNotNull(result);
        assertEquals("UpdatedProduct", result.getName());
        verify(productService, times(1)).updateProduct(any(Product.class));
    }

    @Test
    void testUpdateProduct_NotFound() {
        // Arrange
        ProductDTO productDTO = new ProductDTO(1L, "Product1", "Description1", 10.0, "url1",
                new CategoryDTO(1L, "Category1", "Desc1"));
        when(productService.updateProduct(any(Product.class))).thenReturn(null);

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> productController.updateProduct(productDTO));
        verify(productService, times(1)).updateProduct(any(Product.class));
    }

//    @Test
//    void testCreateProduct() {
//        // Arrange
//        ProductDTO productDTO = new ProductDTO(1L, "NewProduct", "NewDescription", 25.0, "newUrl",
//                new CategoryDTO(1L, "NewCategory", "NewDesc"));
//        Product createdProduct = new Product(1L, "NewProduct", "NewDescription", 25.0, "newUrl",
//                new Category(1L, "NewCategory", "NewDesc"));
//        when(productService.createProduct(any(Product.class))).thenReturn(createdProduct);
//
//        // Act
//        ProductDTO result = productController.createProduct(productDTO);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals("NewProduct", result.getName());
//        verify(productService, times(1)).createProduct(any(Product.class));
//    }
    @Test
    void from() {
    }

    @Test
    void testFrom() {
    }

    @Test
    void testGetProductByName() {
        // Arrange
        List<ESProduct> esProducts = Arrays.asList(
                new ESProduct(1L, "Product1", "Description1"),
                new ESProduct(2L, "Product1", "Description2")
        );
        when(productService.getProductByName("Product1")).thenReturn(esProducts);

        // Act
        List<ESProduct> result = productController.getProductFromName("Product1");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Product1", result.get(0).getName());
        verify(productService, times(1)).getProductByName("Product1");
    }

    @Test
    void testSearchProduct() throws IOException {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product(1L, "Product1", "Description1", 10.0, "url1", new Category(1L, "Category1", "Desc1")),
                new Product(2L, "Product2", "Description2", 20.0, "url2", new Category(2L, "Category2", "Desc2"))
        );
        when(productService.searchProduct("query")).thenReturn(products);

        // Act
        List<ProductDTO> result = productController.searchProduct("query");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Product1", result.get(0).getName());
        verify(productService, times(1)).searchProduct("query");
    }
    @Test
    void testSearchProduct_IOException() throws IOException {
        // Arrange
        when(productService.searchProduct("query")).thenThrow(new IOException("Search failed"));

        // Act & Assert
        assertThrows(IOException.class, () -> productController.searchProduct("query"));
        verify(productService, times(1)).searchProduct("query");
    }
}