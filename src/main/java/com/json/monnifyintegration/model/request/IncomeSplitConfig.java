package com.json.monnifyintegration.model.request;


import lombok.Data;

@Data
public class IncomeSplitConfig {

    private String subAccountCode;
    private Float feePercentage;
    private Float splitPercentage;
    private Float splitAmount;
    private Boolean feeBearer;

}
