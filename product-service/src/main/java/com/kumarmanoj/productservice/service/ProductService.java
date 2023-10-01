package com.kumarmanoj.productservice.service;

import com.kumarmanoj.productservice.dto.ProductRequest;
import com.kumarmanoj.productservice.dto.ProductResponse;
import com.kumarmanoj.productservice.model.Product;
import com.kumarmanoj.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);

        logger.info("Product with "+ product.getId() +" is created");
    }

    public List<ProductResponse> getAllProduct() {
         List<Product> products = productRepository.findAll();
         List<ProductResponse> productResponses = products.stream().map(product -> mapToProductResponse(product)).toList();
         return productResponses;
    }

    public ProductResponse mapToProductResponse(Product product){
            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();
    }
}
