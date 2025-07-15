package com.json.monnifyintegration.model.response;


import lombok.Data;

@Data
public class TokenData {

    private String accessToken;
    private int expiresIn;
}
