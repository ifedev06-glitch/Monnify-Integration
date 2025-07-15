package com.json.monnifyintegration.controller;

import com.json.monnifyintegration.model.response.AuthResponse;
import com.json.monnifyintegration.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/token")
    public AuthResponse getToken() {
        return authService.getAccessToken();
    }
}
