package com.json.monnifyintegration.model.response.wallet;


import com.json.monnifyintegration.model.request.wallet.BvnDetails;
import lombok.Data;

@Data
public class ResponseBody {

    private String walletName;
    private String walletReference;
    private String customerName;
    private String customerEmail;
    private String feeBearer;
    private BvnDetails bvnDetails;
    private String accountNumber;
    private String accountName;

}
