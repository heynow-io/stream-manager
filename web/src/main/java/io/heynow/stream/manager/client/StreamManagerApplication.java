package io.heynow.stream.manager.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StreamManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamManagerApplication.class, args);
    }
}
