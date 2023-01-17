package com.prueba.springboot.eanservice.controller;

import com.prueba.springboot.eanservice.model.entity.Supplier;
import com.prueba.springboot.eanservice.model.rest.SuccessRest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface SupplierController {
    public ResponseEntity<Object> addSupplier(Supplier supplier, BindingResult bindingResult);
    public ResponseEntity<Object> editSupplier(Supplier supplier, BindingResult bindingResult);
    public ResponseEntity<SuccessRest> delSupplier(Integer id);
    public ResponseEntity<Object> getSupplierById(Integer id);
}
