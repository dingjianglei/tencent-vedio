package com.bl.tencent.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.bl.tencent" })
public class VedioApplication {
    public static void main(String[] args) {
        SpringApplication.run(VedioApplication.class, args);
    }
}
