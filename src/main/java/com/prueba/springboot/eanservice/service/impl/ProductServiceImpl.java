package com.prueba.springboot.eanservice.service.impl;

import com.prueba.springboot.eanservice.model.entity.Product;
import com.prueba.springboot.eanservice.model.rest.SuccessRest;
import com.prueba.springboot.eanservice.repository.ProductRepository;
import com.prueba.springboot.eanservice.service.ProductService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<Object> addProduct(Product product, BindingResult bindingResult){
        try{
            product = productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch(ConstraintViolationException e){
            return new ResponseEntity<>(new SuccessRest(false, bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<SuccessRest> delProduct(Integer id){
        try{
            Product product = productRepository.findById(id).get();
            productRepository.delete(product);
            return new ResponseEntity<>(new SuccessRest(true, "Producto eliminado"), HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new SuccessRest(false, "Producto no encontrado"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> editProduct(Product product, BindingResult bindingResult) {
        try{
            productRepository.save(product);
            return new ResponseEntity<>(new SuccessRest(true, "Producto modificado"), HttpStatus.OK);
        } catch(ConstraintViolationException e){
            return new ResponseEntity<>(new SuccessRest(false, bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> getProductById(Integer id){
        try{
            Product product = productRepository.findById(id).get();
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new SuccessRest(false, "Producto no encontrado"), HttpStatus.NOT_FOUND);
        }
    }

}
