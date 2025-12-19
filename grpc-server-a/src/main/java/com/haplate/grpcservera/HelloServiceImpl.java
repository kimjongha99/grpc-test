package com.haplate.grpcservera;

import com.haplate.grpc.hello.HelloRequest;
import com.haplate.grpc.hello.HelloResponse;
import com.haplate.grpc.hello.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

// @GrpcService: 이 클래스를 gRPC 서비스로 등록
@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    // proto에서 정의한 SayHello 메서드 구현
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        // 요청에서 name 추출
        String name = request.getName();

        // 응답 메시지 생성
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage("Hello, " + name + "! From Server-A")
                .build();

        // 응답 전송
        responseObserver.onNext(response);

        // 완료 신호
        responseObserver.onCompleted();
    }
}