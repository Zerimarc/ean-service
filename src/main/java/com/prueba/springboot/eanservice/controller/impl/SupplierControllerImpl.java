package com.prueba.springboot.eanservice.controller.impl;

import com.prueba.springboot.eanservice.controller.SupplierController;
import com.prueba.springboot.eanservice.model.entity.Supplier;
import com.prueba.springboot.eanservice.model.rest.SuccessRest;
import com.prueba.springboot.eanservice.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suppliers")
public class SupplierControllerImpl implements SupplierController {

    @Autowired
    private SupplierService service;

    @Override
    @PostMapping(value = "/add")
    public ResponseEntity<Object> addSupplier(@Valid @RequestBody Supplier supplier, BindingResult bindingResult) {
        return new ResponseEntity<>(service.addSupplier(supplier, bindingResult), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessRest> delSupplier(@PathVariable Integer id) {
        return service.delSupplier(id);
    }

    @Override
    @PostMapping(value = "/edit")
    public ResponseEntity<Object> editSupplier(@Valid @RequestBody Supplier supplier, BindingResult bindingResult) {
        return new ResponseEntity<>(service.editSupplier(supplier, bindingResult), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Object> getSupplierById(@PathVariable Integer id) {
        return service.getSupplierById(id);
    }

}
