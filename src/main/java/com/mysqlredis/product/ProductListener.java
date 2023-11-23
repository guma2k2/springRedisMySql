package com.mysqlredis.product;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductListener {
    private final ProductRedisService productRedisService;

    @PostPersist
    public void postPersist(Product product) {
        productRedisService.clear();
    }

    @PrePersist
    public void prePersist(Product product) {
        // Todo
    }

    @PostUpdate
    public void postUpdate(Product product) {
        productRedisService.clear();
    }

    @PreUpdate
    public void preUpdate(Product product) {

    }
}
