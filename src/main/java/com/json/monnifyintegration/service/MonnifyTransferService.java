package com.json.monnifyintegration.service;

import com.json.monnifyintegration.client.DefaultRestClient;
import com.json.monnifyintegration.model.request.MonnifyTransferRequest;
import com.json.monnifyintegration.model.response.AuthResponse;
import com.json.monnifyintegration.model.response.MonnifyTransferResponseBody;
import com.json.monnifyintegration.model.response.ReservedAccountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
@RequiredArgsConstructor
@Slf4j
public class MonnifyTransferService {

    private final AuthService authService;
    private final DefaultRestClient defaultRestClient;

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


}
