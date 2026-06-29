package com.vicky.CustomerRelationshipManager.elasticRepository;

import com.vicky.CustomerRelationshipManager.document.CustomerDocument;
import com.vicky.CustomerRelationshipManager.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerElasticRepository extends ElasticsearchRepository<CustomerDocument,String> {
    List<CustomerDocument> findByName(String name);
    CustomerDocument findByEmail(String email);
    CustomerDocument findByPhoneNumber(String phoneNumber);
    List<CustomerDocument> findByOccupation(String occupation);
}
