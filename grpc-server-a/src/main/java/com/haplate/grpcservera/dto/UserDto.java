package com.haplate.grpcservera.dto;

import java.util.List;

public record UserDto(
        Long userId,
        String name,
        String email,
        String phone,
        String address,
        List<OrderDto> orders
) {}