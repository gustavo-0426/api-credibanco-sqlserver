package com.co.softworld.credibanco.controller;

import com.co.softworld.credibanco.model.Product;
import com.co.softworld.credibanco.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@Api(tags = "Product Controller")
public class ProductControllerImpl implements IProductController {

    @Autowired
    private IProductService productService;

    @Override
    @PostMapping
    @ApiOperation(value = "Guarda el producto. Recibe en el cuerpo del mensaje el producto y lo procesa.")
    public ResponseEntity<Product> save(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @Override
    @GetMapping("/{productId}")
    @ApiOperation(value = "Consulta un producto. Recibe por parámetro el id del producto, valida que el producto " +
            "exista y genera la consulta. ")
    public ResponseEntity<Product> findById(@PathVariable int productId) {
        return productService.findById(productId);
    }

    @Override
    @GetMapping
    @ApiOperation(value = " Consulta todos los productos creados.")
    public ResponseEntity<List<Product>> findAll() {
        return productService.findAll();
    }

    @Override
    @DeleteMapping("/{productId}")
    @ApiOperation(value = "Elimina un producto. Recibe por parámetro el id del producto, valida que el producto exista " +
            "y procesa la eliminación.")
    public ResponseEntity<Product> delete(@PathVariable int productId) {
        return productService.delete(productId);
    }

}