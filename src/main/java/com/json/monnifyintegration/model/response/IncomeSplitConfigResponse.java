package com.json.monnifyintegration.model.response;


import lombok.Data;

@Data
public class IncomeSplitConfigResponse {

    private String subAccountCode;
    private Float feePercentage;
    private Float splitPercentage;
    private Float splitAmount;
    private Boolean feeBearer;

}
