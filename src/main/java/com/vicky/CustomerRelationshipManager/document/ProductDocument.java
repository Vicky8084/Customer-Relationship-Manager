package com.vicky.CustomerRelationshipManager.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
@Data
@Document(indexName = "product")
public class ProductDocument {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private double price;

    @Field(type = FieldType.Text)
    private LocalDate expiry;

    @Field(type = FieldType.Text)
    private String companyName;

    @Field(type = FieldType.Text)
    private String description;
}
