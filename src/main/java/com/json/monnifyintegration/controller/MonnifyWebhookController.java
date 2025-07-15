package com.json.monnifyintegration.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.monnifyintegration.model.request.webHook.EventDataDto;
import com.json.monnifyintegration.model.request.webHook.MonnifyWebhookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/webhook")
@Slf4j
public class MonnifyWebhookController {

    @Value("${monnify.api.secret}")
    private String monnifySecretKey;

    @PostMapping("/monnify")
    public ResponseEntity<String> handleMonnifyWebhook(
            @RequestBody String rawBody,
            @RequestHeader("monnify-signature") String signatureHeader
    ) {
        try {
            // Verify signature
            log.info(" Raw webhook body:\n{}", rawBody);
            log.info("🛡 Received Monnify signature: {}", signatureHeader);

            String computedSignature = computeHmac256(rawBody, monnifySecretKey);
            if (!computedSignature.equals(signatureHeader)) {
                return ResponseEntity.status(401).body("Invalid signature");
            }

            // Parse JSON to object
            ObjectMapper objectMapper = new ObjectMapper();
            MonnifyWebhookRequest webhookRequest = objectMapper.readValue(rawBody, MonnifyWebhookRequest.class);

            // Handle successful transaction event
            if ("SUCCESSFUL_TRANSACTION".equalsIgnoreCase(webhookRequest.getEventType())) {
                EventDataDto data = webhookRequest.getEventData();

                log.info(" Payment received: accountRef={} | ₦{} | email={}",
                        data.getProduct().getReference(),
                        data.getAmountPaid(),
                        data.getCustomer().getEmail()
                );
            }
            return ResponseEntity.ok("Webhook processed");
        } catch (Exception e) {
            log.error(" Error processing webhook", e);
            return ResponseEntity.status(500).body("Webhook error");
        }
    }

    private String computeHmac256(String data, String secretKey) throws Exception {
        Mac hmacSha256 = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSha256.init(keySpec);
        byte[] hmacBytes = hmacSha256.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hmacBytes);
    }
}
