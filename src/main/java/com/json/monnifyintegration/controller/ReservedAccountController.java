package com.json.monnifyintegration.controller;

import com.json.monnifyintegration.model.request.CreateReservedAccountRequest;
import com.json.monnifyintegration.model.response.ReservedAccountResponse;
import com.json.monnifyintegration.service.ReservedAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservedAccountController {

    private final ReservedAccountService reservedAccountService;

    @PostMapping("/reserved-account")
    public ResponseEntity<ReservedAccountResponse> createReservedAccount(
            @RequestBody CreateReservedAccountRequest request
    ) {
        ReservedAccountResponse response = reservedAccountService.createReservedAccount(request);
        return ResponseEntity.ok(response);
    }

}
