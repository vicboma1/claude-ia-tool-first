package com.example.demo.user;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService service = new UserService();

    @Test
    void shouldExportCsv() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        service.exportUsersCsv().writeTo(out);
        String csv = out.toString("UTF-8");
        assertTrue(csv.contains("email"));
    }

    @Test
    void shouldHandleNullEmail() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        service.exportUsersCsv().writeTo(out);
        String csv = out.toString("UTF-8");
        assertTrue(csv.contains("2,ñoño,"));
    }

    @Test
    void shouldEncodeUtf8() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        service.exportUsersCsv().writeTo(out);
        String csv = out.toString("UTF-8");
        assertTrue(csv.contains("ñoño"));
    }
}
