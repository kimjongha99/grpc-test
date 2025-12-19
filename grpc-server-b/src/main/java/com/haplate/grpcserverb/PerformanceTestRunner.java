//package com.haplate.grpcserverb;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PerformanceTestRunner implements CommandLineRunner {
//
//    private final HelloClientService grpcService;      // gRPC 호출
//    private final HelloRestClientService restService;  // REST 호출
//
//    public PerformanceTestRunner(HelloClientService grpcService,
//                                 HelloRestClientService restService) {
//        this.grpcService = grpcService;
//        this.restService = restService;
//    }
//
//    @Override
//    public void run(String... args) {
//        int iterations = 10000;  // 1000번 반복 호출
//
//        System.out.println("\n========== 성능 비교 테스트 시작 ==========");
//        System.out.println("반복 횟수: " + iterations + "회\n");
//
//        // ===== REST 테스트 =====
//        long restStart = System.currentTimeMillis();
//        for (int i = 0; i < iterations; i++) {
//            restService.sayHelloToA("Test-" + i);
//        }
//        long restEnd = System.currentTimeMillis();
//        long restTime = restEnd - restStart;
//
//        // ===== gRPC 테스트 =====
//        long grpcStart = System.currentTimeMillis();
//        for (int i = 0; i < iterations; i++) {
//            grpcService.sayHelloToA("Test-" + i);
//        }
//        long grpcEnd = System.currentTimeMillis();
//        long grpcTime = grpcEnd - grpcStart;
//
//        // ===== 결과 출력 =====
//        System.out.println("REST 총 소요시간: " + restTime + "ms");
//        System.out.println("gRPC 총 소요시간: " + grpcTime + "ms");
//        System.out.println();
//        System.out.println("REST 평균: " + (restTime / (double) iterations) + "ms/요청");
//        System.out.println("gRPC 평균: " + (grpcTime / (double) iterations) + "ms/요청");
//        System.out.println();
//
//        double ratio = (double) restTime / grpcTime;
//        System.out.println("성능 비율: gRPC가 REST보다 " + String.format("%.2f", ratio) + "배 빠름");
//        System.out.println("============================================\n");
//    }
//}