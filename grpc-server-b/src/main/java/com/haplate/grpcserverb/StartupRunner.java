package com.haplate.grpcserverb;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final HelloClientService helloClientService;

    public StartupRunner(HelloClientService helloClientService) {
        this.helloClientService = helloClientService;
    }

    @Override
    public void run(String... args) {
        // B 시작 후 A에게 인사 요청
        String response = helloClientService.sayHelloToA("Server-B");
        System.out.println("=== A로부터 응답: " + response + " ===");
    }
}
