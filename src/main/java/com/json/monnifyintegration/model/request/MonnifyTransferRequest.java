package com.json.monnifyintegration.model.request;


import lombok.Data;

@Data
public class MonnifyTransferRequest {

    private double amount;
    private String reference;
    private String narration;
    private String destinationBankCode;
    private String destinationAccountNumber;
    private String currency;
    private String sourceAccountNumber;

}
