package com.mysqlredis.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductRedisService productRedisService;


    public List<Product> findAll() {
        List<Product> cachedProducts;
        try {
            cachedProducts = productRedisService.findAll();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error retrieving products from Redis: " + e.getMessage(), e);
        }

        if (cachedProducts == null || cachedProducts.isEmpty()) {
            try {
                cachedProducts = productRepository.findAll();
                if (cachedProducts != null && !cachedProducts.isEmpty()) {
                    productRedisService.saveAll(cachedProducts);
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error retrieving products from the repository: " + e.getMessage(), e);
            }
        }
        return cachedProducts;
    }
    public void saveProduct(ProductCreateRequest request) {
        Product underSave = Product.builder()
                .name(request.name())
                .description(request.desc())
                .build();
        productRepository.save(underSave);
    }
}
