package com.json.monnifyintegration.model.response;

import lombok.Data;

@Data
public class ReservedAccountResponse {

    private boolean requestSuccessful;
    private String responseMessage;
    private String responseCode;
    private ReservedAccountData responseBody;

}
