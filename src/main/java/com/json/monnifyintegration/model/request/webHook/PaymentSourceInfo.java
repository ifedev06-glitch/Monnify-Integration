package com.json.monnifyintegration.model.request.webHook;


import lombok.Data;

@Data
public class PaymentSourceInfo {

    private String bankCode;
    private double amountPaid;
    private String accountName;
    private String sessionId;
    private String accountNumber;

}
