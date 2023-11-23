package com.mysqlredis.product;

public record ProductCreateRequest(

        String name,
        String desc
) {
}
