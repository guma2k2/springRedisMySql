package com.mysqlredis.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductRedisService {
    private final RedisTemplate redisTemplate;

    private final ObjectMapper redisObjectMapper;
    public static final String HASH_KEY = "Product";

    public List<Product> findAll() throws JsonProcessingException {
        String json = (String) redisTemplate.opsForValue().get(HASH_KEY);
        List<Product> productResponses = json != null ?
                redisObjectMapper.readValue(json, new TypeReference<>() {
                }) : null;
        return productResponses;
    }

    public Product findProductById(int id){
        return (Product) redisTemplate.opsForHash().get(HASH_KEY,id);
    }
    public String deleteProduct(int id){
        redisTemplate.opsForHash().delete(HASH_KEY,id);
        return "product removed !!";
    }

    public void clear() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    public void saveAll(List<Product> products) throws JsonProcessingException {
        String json = redisObjectMapper.writeValueAsString(products);
        redisTemplate.opsForValue().set(HASH_KEY,json);
    }
}
