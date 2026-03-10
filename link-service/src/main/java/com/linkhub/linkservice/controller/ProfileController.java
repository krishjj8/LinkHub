package com.linkhub.linkservice.controller;

import com.linkhub.linkservice.dto.ProfileResponseDto;
import com.linkhub.linkservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable String username) {
        ProfileResponseDto profile = userService.getPublicProfile(username);
        return ResponseEntity.ok(profile);
    }
}