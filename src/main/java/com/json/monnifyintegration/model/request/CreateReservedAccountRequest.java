package com.json.monnifyintegration.model.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CreateReservedAccountRequest {

    private String accountReference;
    private String accountName;
    private String currencyCode;
    private String contractCode;
    private String customerEmail;
    private String customerName;
    private String bvn;
    private String nin;
    private boolean getAllAvailableBanks;
    private boolean restrictPaymentSource;
    private List<IncomeSplitConfig> incomeSplitConfig;
    private Map<String, Object> allowedPaymentSource;
    private Map<String, String> metaData;

}
