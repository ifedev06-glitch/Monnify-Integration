package com.json.monnifyintegration.model.request.webHook;


import lombok.Data;

@Data
public class MonnifyWebhookRequest {

    private String eventType;
    private EventDataDto eventData;


}
