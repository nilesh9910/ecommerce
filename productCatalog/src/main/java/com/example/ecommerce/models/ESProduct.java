package com.example.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ESProduct {
    @Id
    Long id;
    String name;
    String description;

}
