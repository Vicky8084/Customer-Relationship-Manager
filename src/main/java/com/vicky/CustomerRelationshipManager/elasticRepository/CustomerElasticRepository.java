package com.vicky.CustomerRelationshipManager.elasticRepository;

import com.vicky.CustomerRelationshipManager.document.CustomerDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerElasticRepository extends ElasticsearchRepository<CustomerDocument,String> {
    List<CustomerDocument> findByName(String name);

}
