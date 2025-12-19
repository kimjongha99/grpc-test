package com.haplate.grpcserverb;

import com.haplate.grpc.hello.HelloRequest;
import com.haplate.grpc.hello.HelloResponse;
import com.haplate.grpc.hello.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class HelloClientService {

    // server-a 연결 (application.properties의 grpc.client.server-a 참조)
    @GrpcClient("server-a")
    private HelloServiceGrpc.HelloServiceBlockingStub helloStub;

    // A에게 인사 요청
    public String sayHelloToA(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();

        HelloResponse response = helloStub.sayHello(request);
        return response.getMessage();
    }
}