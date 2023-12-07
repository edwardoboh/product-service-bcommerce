package com.edwardoboh.productservicebcommerce.service;

import com.edwardoboh.productservicebcommerce.dto.ProductRequest;
import com.edwardoboh.productservicebcommerce.dto.ProductResponse;
import com.edwardoboh.productservicebcommerce.model.Product;
import com.edwardoboh.productservicebcommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
    }

    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::prepareProductResponse).toList();
    }

    private ProductResponse prepareProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
