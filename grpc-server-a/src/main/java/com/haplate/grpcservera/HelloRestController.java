package com.haplate.grpcservera;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    // gRPC와 동일한 로직
    @GetMapping("/hello")
    public HelloDto sayHello(@RequestParam String name) {
        return new HelloDto("Hello, " + name + "! From Server-A");
    }

    public record HelloDto(String message) {}
}