package com.haplate.grpcservera.dto;

import java.util.List;

public record OrderDto(
        Long orderId,
        String orderDate,
        String status,
        Long totalPrice,
        List<ProductDto> products
) {}