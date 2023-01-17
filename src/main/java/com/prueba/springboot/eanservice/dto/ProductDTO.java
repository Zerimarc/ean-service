package com.prueba.springboot.eanservice.dto;

import com.prueba.springboot.eanservice.model.entity.Product;
import com.prueba.springboot.eanservice.model.entity.Supplier;
import com.prueba.springboot.eanservice.model.rest.ProductRest;

public class ProductDTO {

    public static ProductRest productToProductRest(Product product, Supplier supplier){
        ProductRest productRest = new ProductRest();
        productRest.setId(product.getId());
        productRest.setName(product.getName());
        productRest.setPrice(product.getPrice());
        productRest.setSupplier(supplier.getName());
        return productRest;
    }

    public static Product productRestToProduct(ProductRest productRest){
        Product product = new Product();
        product.setId(productRest.getId());
        product.setName(productRest.getName());
        product.setPrice(productRest.getPrice());
        return product;
    }
}
