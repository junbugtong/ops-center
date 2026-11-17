package com.opscenter;

import org.springframework.boot.SpringApplication;

/**
 * Runs the application against a throwaway PostgreSQL container:
 * {@code ./mvnw spring-boot:test-run}. No Compose needed.
 */
public class TestOpsCenterApplication {

    public static void main(String[] args) {
        SpringApplication.from(OpsCenterApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
