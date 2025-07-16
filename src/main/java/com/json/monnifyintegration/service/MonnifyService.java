package com.json.monnifyintegration.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.monnifyintegration.client.DefaultRestClient;
import com.json.monnifyintegration.model.request.CreateReservedAccountRequest;
import com.json.monnifyintegration.model.request.MonnifyTransferRequest;
import com.json.monnifyintegration.model.request.wallet.CreateWalletRequest;
import com.json.monnifyintegration.model.response.AuthResponse;
import com.json.monnifyintegration.model.response.MonnifyTransferResponseBody;
import com.json.monnifyintegration.model.response.ReservedAccountResponse;
import com.json.monnifyintegration.model.response.wallet.CreateWalletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonnifyService {

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

    public CreateWalletResponse CreateWallet(CreateWalletRequest request) throws JsonProcessingException {

        AuthResponse authResponse = authService.getAccessToken();
        String token = authResponse.getResponseBody().getAccessToken();
        RestClient client = defaultRestClient.bearerClient(token);

        // Log full request for debugging
        log.info("Sending Create Wallet Request: \n{}", new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(request));

        try {
            return client.post()
                    .uri("/api/v1/disbursements/wallet")
                    .body(request)
                    .retrieve()
                    .body(CreateWalletResponse.class);

        } catch (HttpClientErrorException e) {
            // Log the actual error response from Monnify
            log.error("Monnify returned an error: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Wallet creation failed: " + e.getResponseBodyAsString(), e);
        }
    }

    public MonnifyTransferResponseBody SingleTransfer(MonnifyTransferRequest request) {

        AuthResponse authResponse = authService.getAccessToken();
        String token = authResponse.getResponseBody().getAccessToken();
        RestClient client = defaultRestClient.bearerClient(token);
        MonnifyTransferResponseBody response = client.post()
                .uri("/api/v2/disbursements/single")
                .body(request)
                .retrieve()
                .body(MonnifyTransferResponseBody.class);
        return response;

    }

    public MonnifyTransferResponseBody SingleTransfer(MonnifyTransferRequest request) {

        AuthResponse authResponse = authService.getAccessToken();
        String token = authResponse.getResponseBody().getAccessToken();
        RestClient client = defaultRestClient.bearerClient(token);
        MonnifyTransferResponseBody response = client.post()
                .uri("/api/v2/disbursements/single")
                .body(request)
                .retrieve()
                .body(MonnifyTransferResponseBody.class);
        return response;

    }

    public ReservedAccountResponse createReservedAccount(CreateReservedAccountRequest request) {
        AuthResponse authResponse = authService.getAccessToken();
        String token = authResponse.getResponseBody().getAccessToken();
        RestClient client = defaultRestClient.bearerClient(token);
        ReservedAccountResponse response = client.post()
                .uri("/api/v2/bank-transfer/reserved-accounts")
                .body(request)
                .retrieve()
                .body(ReservedAccountResponse.class);
        return response;
    }

}
