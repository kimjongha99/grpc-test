package com.haplate.grpcservera;

import com.haplate.grpc.user.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUserWithOrders(UserRequest request, StreamObserver<UserWithOrdersResponse> responseObserver) {

        // 더미 데이터 생성 (주문 5개, 각 주문당 상품 3개)
        List<Order> orders = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            List<Product> products = new ArrayList<>();

            for (int j = 1; j <= 3; j++) {
                Product product = Product.newBuilder()
                        .setProductId(j)
                        .setName("상품-" + i + "-" + j)
                        .setDescription("이것은 상품 설명입니다. 상품 " + i + "-" + j + "에 대한 상세 정보가 여기에 들어갑니다.")
                        .setPrice(10000 * j)
                        .setQuantity(j)
                        .build();
                products.add(product);
            }

            Order order = Order.newBuilder()
                    .setOrderId(i)
                    .setOrderDate("2024-01-" + String.format("%02d", i))
                    .setStatus("COMPLETED")
                    .setTotalPrice(products.stream().mapToLong(p -> p.getPrice() * p.getQuantity()).sum())
                    .addAllProducts(products)
                    .build();
            orders.add(order);
        }

        // 응답 생성
        UserWithOrdersResponse response = UserWithOrdersResponse.newBuilder()
                .setUserId(request.getUserId())
                .setName("홍길동")
                .setEmail("hong@example.com")
                .setPhone("010-1234-5678")
                .setAddress("서울시 강남구 테헤란로 123")
                .addAllOrders(orders)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}