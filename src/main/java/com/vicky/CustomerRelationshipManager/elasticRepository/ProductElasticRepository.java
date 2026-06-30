package com.vicky.CustomerRelationshipManager.elasticRepository;

import com.vicky.CustomerRelationshipManager.document.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductElasticRepository extends ElasticsearchRepository<ProductDocument,String> {
    List<ProductDocument> findByName(String name);
    List<ProductDocument> findByCompanyName(String companyName);
    List<ProductDocument> findByExpiryBefore(LocalDate date);
    List<ProductDocument> findByPriceBetween(double minPrice, double maxPrice);
}
