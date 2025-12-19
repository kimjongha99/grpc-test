package com.haplate.grpcservera;

import com.haplate.grpcservera.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    @GetMapping("/user")
    public UserDto getUserWithOrders(@RequestParam Long userId) {

        // gRPC와 동일한 더미 데이터 생성
        List<OrderDto> orders = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            List<ProductDto> products = new ArrayList<>();

            for (int j = 1; j <= 3; j++) {
                ProductDto product = new ProductDto(
                        (long) j,
                        "상품-" + i + "-" + j,
                        "이것은 상품 설명입니다. 상품 " + i + "-" + j + "에 대한 상세 정보가 여기에 들어갑니다.",
                        10000L * j,
                        j
                );
                products.add(product);
            }

            long totalPrice = products.stream()
                    .mapToLong(p -> p.price() * p.quantity())
                    .sum();

            OrderDto order = new OrderDto(
                    (long) i,
                    "2024-01-" + String.format("%02d", i),
                    "COMPLETED",
                    totalPrice,
                    products
            );
            orders.add(order);
        }

        return new UserDto(
                userId,
                "홍길동",
                "hong@example.com",
                "010-1234-5678",
                "서울시 강남구 테헤란로 123",
                orders
        );
    }
}