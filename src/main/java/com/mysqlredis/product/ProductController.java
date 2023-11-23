package com.mysqlredis.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping
    public String save(@RequestBody ProductCreateRequest request) {
        productService.saveProduct(request);
        return "save successfully";
    }
}
