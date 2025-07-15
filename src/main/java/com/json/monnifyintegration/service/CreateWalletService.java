package com.json.monnifyintegration.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.monnifyintegration.client.DefaultRestClient;
import com.json.monnifyintegration.model.request.MonnifyTransferRequest;
import com.json.monnifyintegration.model.request.wallet.CreateWalletRequest;
import com.json.monnifyintegration.model.response.AuthResponse;
import com.json.monnifyintegration.model.response.MonnifyTransferResponseBody;
import com.json.monnifyintegration.model.response.wallet.CreateWalletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateWalletService {

    private final AuthService authService;
    private final DefaultRestClient defaultRestClient;

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
}
