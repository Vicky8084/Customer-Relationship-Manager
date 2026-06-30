package com.vicky.CustomerRelationshipManager.elasticConverter;

import com.vicky.CustomerRelationshipManager.document.ProductDocument;
import com.vicky.CustomerRelationshipManager.model.Product;

public class ProductElasticConvertor {
    public static Product convertProductDocumentIntoProduct(ProductDocument productDocument){
        Product product=new Product();
        product.setName(productDocument.getName());
        product.setId(Long.parseLong(productDocument.getId()));
        product.setPrice(productDocument.getPrice());
        product.setExpiry(productDocument.getExpiry());
        product.setCompanyName(productDocument.getCompanyName());
        product.setDescription(productDocument.getDescription());
        return product;
    }

    public static ProductDocument convertProductIntoProductDocument(Product product){
        ProductDocument productDocument=new ProductDocument();
        productDocument.setId(String.valueOf(product.getId()));
        productDocument.setName(product.getName());
        productDocument.setPrice(product.getPrice());
        product.setExpiry(product.getExpiry());
        productDocument.setCompanyName(product.getCompanyName());
        productDocument.setDescription(product.getDescription());
        return productDocument;
    }
}
