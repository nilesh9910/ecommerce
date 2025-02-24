package com.example.ecommerce.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.ecommerce.models.ESProduct;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.repos.ESProductRepo;
import com.example.ecommerce.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Primary
@Service
public class ProductService implements IProductService{

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ESProductRepo esProductRepo;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product createProduct(Product product) {
        Product savedProduct = productRepo.save(product);
        ESProduct esProduct = new ESProduct();
        esProduct.setId(savedProduct.getId());
        esProduct.setName(savedProduct.getName());
        esProduct.setDescription(savedProduct.getDescription());
        esProductRepo.save(esProduct);
        return savedProduct;

    }
    public Product updateProduct(Product product) {
        Optional<Product> optionalProduct = productRepo.findById(product.getId());
        if(optionalProduct.isEmpty()) {
            return null;
        }
        return productRepo.save(product);
    }
    public List<ESProduct> getProductByName(String name) {
        return esProductRepo.findByName(name);
    }
    public List<Product> searchProduct(String search) throws IOException {
        try {
            SearchResponse<ESProduct> response = elasticsearchClient.search(s -> s
                            .index("product") // Index name
                            .query(q -> q.multiMatch(m -> m
                                    .query(search)
                                    .fields(Arrays.asList("name", "description"))
                                    .fuzziness("AUTO")
                                    .type(co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType.BestFields)
                            )),
                    ESProduct.class);
            System.out.println(response.toString());
            List<ESProduct> esProducts = response.hits().hits().stream()
                    .map(Hit::source)
                    .toList();
            List<Long> ids = new ArrayList<>();
            for (ESProduct esProduct : esProducts) {
                System.out.println(esProduct.getName());
                ids.add(esProduct.getId());
            }
            System.out.println(ids);
            return productRepo.findAllById(ids);
        }
        catch (IOException e) {
            throw e;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return new ArrayList<>();
    }
}
