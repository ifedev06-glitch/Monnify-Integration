package com.json.monnifyintegration.model.response;


import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class MonnifyTransferResponseBody {

    private double amount;
    private String reference;
    private String status;
    private ZonedDateTime dateCreated;
    private double totalFee;
    private String destinationAccountName;
    private String destinationBankName;
    private String destinationAccountNumber;
    private String destinationBankCode;

}
