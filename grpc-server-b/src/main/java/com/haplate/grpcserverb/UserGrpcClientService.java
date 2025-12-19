package com.haplate.grpcserverb;

import com.haplate.grpc.user.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserGrpcClientService {

    @GrpcClient("server-a")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    // gRPC로 A에게 사용자+주문 정보 요청
    public UserWithOrdersResponse getUserWithOrders(Long userId) {
        UserRequest request = UserRequest.newBuilder()
                .setUserId(userId)
                .build();

        return userStub.getUserWithOrders(request);
    }
}