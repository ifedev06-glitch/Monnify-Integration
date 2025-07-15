package com.json.monnifyintegration.model.response;

import lombok.Data;

@Data
public class ReservedAccount {

    private String bankCode;
    private String bankName;
    private String accountNumber;
    private String accountName;

}
