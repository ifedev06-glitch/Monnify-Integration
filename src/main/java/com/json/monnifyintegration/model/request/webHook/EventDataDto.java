package com.json.monnifyintegration.model.request.webHook;


import lombok.Data;

import java.util.List;

@Data
public class EventDataDto {

    private ProductDto product;
    private String transactionReference;
    private String paymentReference;
    private String paidOn;
    private String paymentDescription;
    private Object metaData;
    private List<PaymentSourceInfo> paymentSourceInformation;
    private DestinationAccountInfo destinationAccountInformation;
    private double amountPaid;
    private double totalPayable;
    private Object cardDetails;
    private String paymentMethod;
    private String currency;
    private String settlementAmount;
    private String paymentStatus;
    private CustomerDto customer;

}
