package com.nkoder.SpringEcom.controller;

import com.nkoder.SpringEcom.model.Product;
import com.nkoder.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
//@CrossOrigin("*")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/allproducts")
    public List<Product> getProducts(){
        return productService.getAllProducts();

    }

    @GetMapping("/product/{pId}")
    public ResponseEntity<Product> getProductById(@PathVariable int pId) {
        return productService.getById(pId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/products/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name){
        return productService.getByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

}
