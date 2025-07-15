package com.json.monnifyintegration.controller;


import com.json.monnifyintegration.model.request.CreateReservedAccountRequest;
import com.json.monnifyintegration.model.request.MonnifyTransferRequest;
import com.json.monnifyintegration.model.response.MonnifyTransferResponseBody;
import com.json.monnifyintegration.model.response.ReservedAccountResponse;
import com.json.monnifyintegration.service.MonnifyTransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class MonnifyAccountController {

    private final MonnifyTransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<MonnifyTransferResponseBody> createReservedAccount(
            @RequestBody MonnifyTransferRequest request) {
        MonnifyTransferResponseBody response = transferService.SingleTransfer(request);
        return ResponseEntity.ok(response);
    }

}
