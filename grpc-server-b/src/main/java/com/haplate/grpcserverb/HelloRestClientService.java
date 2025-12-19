package com.haplate.grpcserverb;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloRestClientService {

    // REST 호출용 클라이언트
    private final RestTemplate restTemplate;

    public HelloRestClientService() {
        this.restTemplate = new RestTemplate();
    }

    // REST로 A에게 요청
    public String sayHelloToA(String name) {
        // A의 REST endpoint 호출
        String url = "http://localhost:8080/hello?name=" + name;

        // GET 요청 → JSON 응답 → HelloDto로 변환
        HelloDto response = restTemplate.getForObject(url, HelloDto.class);

        return response != null ? response.message() : "No response";
    }

    // A의 응답 구조와 동일하게 맞춤
    public record HelloDto(String message) {}
}