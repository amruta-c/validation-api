package com.intuit.validationapi.service;

import com.intuit.validationapi.model.BusinessProfile;
import com.intuit.validationapi.model.SubscriptionProducts;
import com.intuit.validationapi.model.ValidationResponse;
import com.intuit.validationapi.model.ValidationStatus;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {

    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);
    private final JSONParser parser = new JSONParser();
    @Value("${business.profile.validator.config.file}")
    private String validationConfigFile;

    public ResponseEntity<ValidationResponse> validate(BusinessProfile businessProfile, List<SubscriptionProducts> products) {
        return ResponseEntity.ok().body(ValidationResponse.builder()
                .productId(businessProfile.getId())
                .validationMessage("Profile has been validated successfully")
                .status(ValidationStatus.SUCCESSFUL)
                .build());
    }
}
