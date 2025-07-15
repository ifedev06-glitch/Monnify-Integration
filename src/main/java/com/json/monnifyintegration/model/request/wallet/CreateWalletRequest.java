package com.json.monnifyintegration.model.request.wallet;


import lombok.Data;

@Data
public class CreateWalletRequest {

    private String walletReference;
    private String walletName;
    private String customerName;
    private String customerEmail;
    private BvnDetails bvnDetails;

}
