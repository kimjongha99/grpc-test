package com.haplate.grpcservera.dto;

public record ProductDto(
        Long productId,
        String name,
        String description,
        Long price,
        Integer quantity
) {}