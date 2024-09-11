package org.example.bdus2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Bdus2Application {

    public static void main(String[] args) {
        SpringApplication.run(Bdus2Application.class, args);
    }

}
