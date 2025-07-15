package com.json.monnifyintegration.model.request.webHook;


import lombok.Data;

@Data
public class DestinationAccountInfo {

    private String bankCode;
    private String bankName;
    private String accountNumber;


}
