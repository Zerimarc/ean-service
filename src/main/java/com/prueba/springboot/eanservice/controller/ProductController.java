package com.prueba.springboot.eanservice.controller;

import com.prueba.springboot.eanservice.model.entity.Product;
import com.prueba.springboot.eanservice.model.rest.SuccessRest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ProductController {

    public ResponseEntity<Object> addProduct(Product product, BindingResult bindingResult);
    public ResponseEntity<SuccessRest> delProduct(Integer id);
    public ResponseEntity<Object> editProduct(Product product, BindingResult bindingResult);
    public ResponseEntity<Object> getProductById(Integer id);
    public ResponseEntity<Object> getProductInfoByEAN(String ean);

}
