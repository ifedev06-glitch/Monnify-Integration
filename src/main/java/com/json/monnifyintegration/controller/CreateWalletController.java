package com.json.monnifyintegration.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.json.monnifyintegration.model.request.wallet.CreateWalletRequest;
import com.json.monnifyintegration.model.response.wallet.CreateWalletResponse;
import com.json.monnifyintegration.service.CreateWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CreateWalletController {

    public final CreateWalletService createWalletService;

    @PostMapping("/createWallet")
    public ResponseEntity<CreateWalletResponse> createWallet( @RequestBody CreateWalletRequest request) throws JsonProcessingException {
        CreateWalletResponse response = createWalletService.CreateWallet(request);
        return ResponseEntity.ok(response);

    }

}
