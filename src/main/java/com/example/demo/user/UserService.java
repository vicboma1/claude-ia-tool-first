package com.example.demo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public Mono<String> exportUsersCsv() {
        String csv = "id,name,email\n1,john,john@test.com";
        if (csv == null) {
            throw new IllegalStateException("CSV export returned null");
        }
        log.info("CSV export requested");
        return Mono.just(csv);
    }
}
