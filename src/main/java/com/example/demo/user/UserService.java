package com.example.demo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Mono;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public Mono<StreamingResponseBody> exportUsersCsv() {
        StreamingResponseBody body = outputStream -> {
            try (PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
                writer.println("id,name,email");
                streamUsers().forEach(user -> {
                    String email = user.email() != null ? user.email() : "";
                    writer.println(user.id() + "," + user.name() + "," + email);
                });
                log.info("CSV export completed");
            }
        };
        if (body == null) {
            throw new IllegalStateException("CSV export returned null");
        }
        return Mono.just(body);
    }

    Stream<User> streamUsers() {
        return Stream.of(
            new User(1L, "john", "john@test.com"),
            new User(2L, "ñoño", null)
        );
    }
}
