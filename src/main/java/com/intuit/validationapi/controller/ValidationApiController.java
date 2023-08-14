package com.intuit.validationapi.controller;

import com.intuit.validationapi.model.BusinessProfile;
import com.intuit.validationapi.model.ValidationResponse;
import com.intuit.validationapi.service.ValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
public class ValidationApiController {

    private final ValidationService validationService;

    public ValidationApiController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<ValidationResponse> validate(@RequestBody BusinessProfile profile) {
        return validationService.validate(profile, profile.getSubscriptionProducts());
    }
}
