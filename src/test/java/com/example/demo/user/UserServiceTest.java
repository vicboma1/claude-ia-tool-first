package com.example.demo.user;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService service = new UserService();

    @Test
    void shouldExportCsv() {
        Mono<String> result = service.exportUsersCsv();
        String csv = result.block();
        assertTrue(csv.contains("email"));
    }

    @Test
    void shouldReturnMono() {
        assertInstanceOf(Mono.class, service.exportUsersCsv());
    }

    @Test
    void shouldContainHeader() {
        String csv = service.exportUsersCsv().block();
        assertTrue(csv.startsWith("id,name,email"));
    }
}
