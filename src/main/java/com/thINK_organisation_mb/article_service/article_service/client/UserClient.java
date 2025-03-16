package com.thINK_organisation_mb.article_service.article_service.client;

import com.thINK_organisation_mb.article_service.article_service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Component
@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserClient {
    @GetMapping("/users/{userId}")
    ResponseEntity<UserDTO> getUser(@PathVariable UUID userId);

    @GetMapping("users/{userId1}/{userId2}")
    ResponseEntity<Boolean> follows(@PathVariable UUID userId1, @PathVariable UUID userId2);

}