package com.example.ecommerce.repos;

import com.example.ecommerce.models.ESProduct;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ESProductRepo extends ElasticsearchRepository<ESProduct, Long> {

    @Query("{\"match\": {\"name\": \"?0\"}}")
    List<ESProduct> findByName(String name);
}
