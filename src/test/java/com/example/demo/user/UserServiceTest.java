package com.example.demo.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    @Test
    void shouldExportCsv() {
        UserService service = new UserService();
        String csv = service.exportUsersCsv();
        assertTrue(csv.contains("email"));
    }
}
