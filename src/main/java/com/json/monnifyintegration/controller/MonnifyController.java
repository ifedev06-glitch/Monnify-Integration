package com.json.monnifyintegration.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.json.monnifyintegration.client.DefaultRestClient;
import com.json.monnifyintegration.model.request.CreateReservedAccountRequest;
import com.json.monnifyintegration.model.request.MonnifyTransferRequest;
import com.json.monnifyintegration.model.request.wallet.CreateWalletRequest;
import com.json.monnifyintegration.model.response.AuthResponse;
import com.json.monnifyintegration.model.response.MonnifyTransferResponseBody;
import com.json.monnifyintegration.model.response.ReservedAccountResponse;
import com.json.monnifyintegration.model.response.wallet.CreateWalletResponse;
import com.json.monnifyintegration.service.AuthService;
import com.json.monnifyintegration.service.MonnifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MonnifyController {


    private final AuthService authService;
    private final MonnifyService monnifyService;

    @GetMapping("/token")
    public AuthResponse getToken() {
        return authService.getAccessToken();
    }

    @PostMapping("/createWallet")
    public ResponseEntity<CreateWalletResponse> createWallet(@RequestBody CreateWalletRequest request) throws JsonProcessingException {
        CreateWalletResponse response = monnifyService.CreateWallet(request);
        return ResponseEntity.ok(response);

    }
    @PostMapping("/transfer")
    public ResponseEntity<MonnifyTransferResponseBody> createReservedAccount(
            @RequestBody MonnifyTransferRequest request) {
        MonnifyTransferResponseBody response = monnifyService.SingleTransfer(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/reserved-account")
    public ResponseEntity<ReservedAccountResponse> createReservedAccount(
            @RequestBody CreateReservedAccountRequest request
    ) {
        ReservedAccountResponse response = monnifyService.createReservedAccount(request);
        return ResponseEntity.ok(response);
    }

}
