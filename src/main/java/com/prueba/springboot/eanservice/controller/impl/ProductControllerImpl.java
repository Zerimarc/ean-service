package com.prueba.springboot.eanservice.controller.impl;

import com.prueba.springboot.eanservice.controller.ProductController;
import com.prueba.springboot.eanservice.model.entity.Product;
import com.prueba.springboot.eanservice.model.rest.SuccessRest;
import com.prueba.springboot.eanservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductService service;

    @Override
    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        return new ResponseEntity<>(service.addProduct(product, bindingResult), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessRest> delProduct(@PathVariable Integer id){
        return service.delProduct(id);
    }

    @Override
    @PostMapping("/edit")
    public ResponseEntity<Object> editProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        return new ResponseEntity<>(service.editProduct(product, bindingResult), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Integer id){
        return service.getProductById(id);
    }

    @Override
    @GetMapping("/search/{ean}")
    public ResponseEntity<Object> getProductInfoByEAN(@PathVariable String ean){
        return service.getProductInfoByEAN(ean);
    }

}
