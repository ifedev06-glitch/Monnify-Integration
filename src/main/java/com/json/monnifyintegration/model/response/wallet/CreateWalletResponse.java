package com.json.monnifyintegration.model.response.wallet;


import lombok.Data;

@Data
public class CreateWalletResponse {

    private boolean requestSuccessful;
    private String responseMessage;
    private String responseCode;
    private ResponseBody responseBody;


}
