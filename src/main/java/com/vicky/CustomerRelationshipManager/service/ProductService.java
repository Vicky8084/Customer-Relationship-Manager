package com.vicky.CustomerRelationshipManager.service;
import com.vicky.CustomerRelationshipManager.converter.ProductConverter;
import com.vicky.CustomerRelationshipManager.dbRepository.ProductRepository;
import com.vicky.CustomerRelationshipManager.document.ProductDocument;
import com.vicky.CustomerRelationshipManager.dto.ProductRequestDto;
import com.vicky.CustomerRelationshipManager.elasticConverter.ProductElasticConvertor;
import com.vicky.CustomerRelationshipManager.elasticRepository.ProductElasticRepository;
import com.vicky.CustomerRelationshipManager.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductElasticRepository productElasticRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          ProductElasticRepository productElasticRepository) {
        this.productRepository = productRepository;
        this.productElasticRepository=productElasticRepository;
    }

    public Product saveProduct(ProductRequestDto productRequestDto){
        Product product= ProductConverter.convertProductRequestDtoIntoProduct(productRequestDto);
        Product savedProduct=productRepository.save(product);

        ProductDocument productDocument= ProductElasticConvertor.convertProductIntoProductDocument(savedProduct);
        productElasticRepository.save(productDocument);
        return savedProduct;
    }

    public Product findById(Long id){
        Optional<Product> productOptional= productRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new RuntimeException("Product not found with ID :- "+id);
        }
        return productOptional.get();
    }

    public List<Product> findAll(){
        Iterable<ProductDocument> productDocuments=productElasticRepository.findAll();
        ArrayList<Long> ids=new ArrayList<>();
        for(ProductDocument productDocument : productDocuments){
            ids.add(Long.parseLong(productDocument.getId()));
        }
        return productRepository.findAllById(ids);
    }

    public List<Product> findByName(String name){
        List<ProductDocument> productDocuments =productElasticRepository.findByName(name);
        ArrayList<Long> ids=new ArrayList<>();
        for(ProductDocument productDocument : productDocuments){
            ids.add(Long.parseLong(productDocument.getId()));
        }
        return productRepository.findAllById(ids);
    }

    public List<Product> findByCompanyName(String companyName){
        List<ProductDocument> productDocuments=productElasticRepository.findByCompanyName(companyName);
        ArrayList<Long> ids=new ArrayList<>();
        for (ProductDocument productDocument : productDocuments){
            ids.add(Long.parseLong(productDocument.getId()));
        }
        return productRepository.findAllById(ids);
    }

    public Product updateById(Long id, ProductRequestDto productRequestDto){
        Product product=findById(id);
        product.setName(productRequestDto.getName().trim());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setExpiry(productRequestDto.getExpiry());
        product.setCompanyName(productRequestDto.getCompanyName().trim());
        product.setDescription(productRequestDto.getDescription().trim());
        productRepository.save(product);
        return product;
    }

    public void deleteById(Long id){
        Product product=findById(id);
        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(String name, String companyName){
        HashSet<Long> ids=new HashSet<>();
        if(name!=null){
            List<Product> productList=findByName(name);
            for (Product product : productList){
                ids.add(product.getId());
            }
        }
        if (companyName!=null){
            List<Product> productList = findByCompanyName(companyName);
            for (Product product : productList){
                ids.add(product.getId());
            }
        }
        return productRepository.findAllById(ids);
    }

    public List<Product> searchByExpiry(Long day){
        LocalDate targetDate = LocalDate.now().plusDays(day);
        List<ProductDocument> productDocuments=productElasticRepository.findByExpiryBefore(targetDate);
        ArrayList<Long> ids=new ArrayList<>();
        for(ProductDocument productDocument : productDocuments){
            ids.add(Long.parseLong(productDocument.getId()));
        }
        return productRepository.findAllById(ids);
    }

    public List<Product> findByPriceBetween(double minPrice, double maxPrice){
        List<ProductDocument> productDocuments=productElasticRepository.findByPriceBetween(minPrice,maxPrice);
        ArrayList<Long> ids=new ArrayList<>();
        for(ProductDocument productDocument : productDocuments){
            ids.add(Long.parseLong(productDocument.getId()));
        }
        return productRepository.findAllById(ids);
    }
}

