package com.example.demo.user;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService service = new UserService();

    @Test
    void shouldExportCsv() throws Exception {
        StreamingResponseBody body = service.exportUsersCsv().block();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        body.writeTo(out);
        assertTrue(out.toString("UTF-8").contains("email"));
    }

    @Test
    void shouldHandleNullEmail() throws Exception {
        StreamingResponseBody body = service.exportUsersCsv().block();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        body.writeTo(out);
        assertTrue(out.toString("UTF-8").contains("2,ñoño,"));
    }

    @Test
    void shouldEncodeUtf8() throws Exception {
        StreamingResponseBody body = service.exportUsersCsv().block();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        body.writeTo(out);
        assertTrue(out.toString("UTF-8").contains("ñoño"));
    }

    @Test
    void shouldReturnMono() {
        assertInstanceOf(Mono.class, service.exportUsersCsv());
    }

    @Test
    void shouldContainHeader() throws Exception {
        StreamingResponseBody body = service.exportUsersCsv().block();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        body.writeTo(out);
        assertTrue(out.toString("UTF-8").startsWith("id,name,email"));
    }
}
