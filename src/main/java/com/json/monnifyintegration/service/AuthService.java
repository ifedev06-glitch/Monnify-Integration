package com.json.monnifyintegration.service;

import com.json.monnifyintegration.client.DefaultRestClient;
import com.json.monnifyintegration.model.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${monnify.api.key}")
    private String apiKey;

    @Value("${monnify.api.secret}")
    private String secretKey;

    private final DefaultRestClient defaultRestClient;

    private String accessToken;
    private long expiryTime;
    private AuthResponse lastAuthResponse;

    public AuthResponse getAccessToken() {
        if (accessToken != null && System.currentTimeMillis() < expiryTime) {
            return lastAuthResponse;
        }
        String encoded = Base64.getEncoder()
                .encodeToString((apiKey + ":" + secretKey).getBytes(StandardCharsets.UTF_8));
        RestClient client = defaultRestClient.basicClient(encoded);
        AuthResponse response = client.post()
                .uri("/api/v1/auth/login")
                .retrieve()
                .body(AuthResponse.class);
        accessToken = response.getResponseBody().getAccessToken();
        expiryTime = System.currentTimeMillis() + (response.getResponseBody().getExpiresIn() * 1000);
        lastAuthResponse = response;

        return lastAuthResponse;
    }
}
