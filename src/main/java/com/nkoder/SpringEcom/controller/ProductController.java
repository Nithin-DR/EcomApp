package com.nkoder.SpringEcom.controller;

import com.nkoder.SpringEcom.model.Product;
import com.nkoder.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<Product> getProducts() {
        return productService.getAllProducts();

    }

    @GetMapping("/product/{pId}")
    public ResponseEntity<Product> getProductById(@PathVariable int pId) {
        return productService.getById(pId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/products/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        return productService.getByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping(
            value = "/product/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> updateProduct(
            @PathVariable int id,
            @RequestPart("product") Product product,
            @RequestPart("imageFile") MultipartFile imageFile
    ) throws IOException {

        product.setId(id);
        Product updatedProduct = productService.addOrUpdateProduct(product, imageFile);
        System.out.println("File name: " + imageFile.getOriginalFilename());
        System.out.println("File type: " + imageFile.getContentType());

        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping(
            value = "/product/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> saveProduct(
            @PathVariable int id,
            @RequestPart("product") Product product,
            @RequestPart("imageFile") MultipartFile imageFile
    ) throws IOException {

        product.setId(id);
        Product updatedProduct = productService.addOrUpdateProduct(product, imageFile);
        System.out.println("File name: " + imageFile.getOriginalFilename());
        System.out.println("File type: " + imageFile.getContentType());

        return ResponseEntity.ok(updatedProduct);
    }


    //If request has file + JSON → use @RequestPart
    // @RequestBody → only JSON
    // @RequestPart → multipart/form-data


    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Optional<Product> product = productService.getById(id);
        if (product != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        System.out.println("searching with :" + keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}

