package com.json.monnifyintegration.model.response;


import lombok.Data;

import java.util.List;

@Data
public class ReservedAccountData {

    private String contractCode;
    private String accountReference;
    private String accountName;
    private String currencyCode;
    private String customerEmail;
    private String customerName;
    private List<ReservedAccount> accounts;
    private String collectionChannel;
    private String reservationReference;
    private String reservedAccountType;
    private String status;
    private String createdOn;
    private List<IncomeSplitConfigResponse> incomeSplitConfig;
    private String bvn;
    private boolean restrictPaymentSource;

}
