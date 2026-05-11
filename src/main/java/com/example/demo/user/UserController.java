package com.example.demo.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/api/users/export", produces = "text/csv;charset=UTF-8")
    public Mono<ResponseEntity<StreamingResponseBody>> exportUsers() {
        return userService.exportUsersCsv()
                .map(body -> ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("text/csv;charset=UTF-8"))
                        .body(body));
    }
}
