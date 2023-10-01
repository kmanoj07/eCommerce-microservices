package com.kumarmanoj.productservice.controller;

import com.kumarmanoj.productservice.dto.ProductRequest;
import com.kumarmanoj.productservice.dto.ProductResponse;
import com.kumarmanoj.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);

        System.out.println("Product created Successfully");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductResponse> getAllProduct() {
        return productService.getAllProduct();
    }
}
