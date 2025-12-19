package com.haplate.grpcserverb;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserRestClientService {

    private final RestTemplate restTemplate;

    public UserRestClientService() {
        this.restTemplate = new RestTemplate();
    }

    // REST로 A에게 사용자+주문 정보 요청
    public UserDto getUserWithOrders(Long userId) {
        String url = "http://localhost:8080/user?userId=" + userId;
        return restTemplate.getForObject(url, UserDto.class);
    }

    // A의 응답 구조와 매핑할 DTO
    public record UserDto(
            Long userId,
            String name,
            String email,
            String phone,
            String address,
            List<OrderDto> orders
    ) {}

    public record OrderDto(
            Long orderId,
            String orderDate,
            String status,
            Long totalPrice,
            List<ProductDto> products
    ) {}

    public record ProductDto(
            Long productId,
            String name,
            String description,
            Long price,
            Integer quantity
    ) {}
}