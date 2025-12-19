package com.haplate.grpcserverb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ComplexObjectTestRunner implements CommandLineRunner {

    private final UserGrpcClientService grpcService;
    private final UserRestClientService restService;

    public ComplexObjectTestRunner(UserGrpcClientService grpcService,
                                   UserRestClientService restService) {
        this.grpcService = grpcService;
        this.restService = restService;
    }

    @Override
    public void run(String... args) {
        int iterations = 10000;

        System.out.println("\n========== 복잡한 객체 성능 비교 ==========");
        System.out.println("구조: User + 5개 Order + 각 3개 Product (총 15개 상품)");
        System.out.println("반복 횟수: " + iterations + "회\n");

        // ===== REST 테스트 =====
        long restStart = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            restService.getUserWithOrders((long) i);
        }
        long restEnd = System.currentTimeMillis();
        long restTime = restEnd - restStart;

        // ===== gRPC 테스트 =====
        long grpcStart = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            grpcService.getUserWithOrders((long) i);
        }
        long grpcEnd = System.currentTimeMillis();
        long grpcTime = grpcEnd - grpcStart;

        // ===== 결과 출력 =====
        System.out.println("REST 총 소요시간: " + restTime + "ms");
        System.out.println("gRPC 총 소요시간: " + grpcTime + "ms");
        System.out.println();
        System.out.println("REST 평균: " + String.format("%.4f", restTime / (double) iterations) + "ms/요청");
        System.out.println("gRPC 평균: " + String.format("%.4f", grpcTime / (double) iterations) + "ms/요청");
        System.out.println();

        double ratio = (double) restTime / grpcTime;
        System.out.println("성능 비율: gRPC가 REST보다 " + String.format("%.2f", ratio) + "배 빠름");
        System.out.println("==========================================\n");
    }
}