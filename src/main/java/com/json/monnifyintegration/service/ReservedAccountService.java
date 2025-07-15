package com.json.monnifyintegration.service;


import com.json.monnifyintegration.client.DefaultRestClient;
import com.json.monnifyintegration.model.request.CreateReservedAccountRequest;
import com.json.monnifyintegration.model.response.AuthResponse;
import com.json.monnifyintegration.model.response.ReservedAccountResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Data
@RequiredArgsConstructor
@Service
public class ReservedAccountService {

    private final AuthService authService;
    private final DefaultRestClient defaultRestClient;

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
