package com.mysqlredis.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(ProductListener.class)
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
