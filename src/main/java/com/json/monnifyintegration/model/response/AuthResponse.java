package com.json.monnifyintegration.model.response;


import lombok.Data;

@Data
public class AuthResponse {

    private boolean requestSuccessful;
    private String responseMessage;
    private String responseCode;
    private TokenData responseBody;
}
