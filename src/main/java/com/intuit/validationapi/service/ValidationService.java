package com.intuit.validationapi.service;

import com.intuit.validationapi.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {

    public ResponseEntity<ValidationResponse> validate(BusinessProfile profile, String product) {
        try {
            if (validateBusinessProfile(profile)) {
                return ResponseEntity.ok(ValidationResponse.builder()
                        .productId(product)
                        .status(ValidationStatus.SUCCESSFUL)
                        .validationMessage("Data is valid. Validation done by " + product + " product")
                        .build());
            } else {
                return ResponseEntity.badRequest().body(ValidationResponse.builder()
                        .productId(product)
                        .status(ValidationStatus.FAILED)
                        .validationMessage("Invalid data. According to field configuration, it should comply " +
                                "with the provided regular expression. This validation is performed by " + product + " application.")
                        .build());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ValidationResponse.builder()
                            .productId(product)
                            .status(ValidationStatus.FAILED)
                            .validationMessage("An error occurred during validation: " + e.getMessage())
                            .build());
        }
    }

    private boolean validateBusinessProfile(BusinessProfile accounting) {
        return validateCompanyName(accounting.getCompanyName())
                && validateLegalName(accounting.getLegalName())
                && validateAddress(accounting.getBusinessAddress())
                && validateAddress(accounting.getLegalAddress())
                && validateTaxIdentifiers(accounting.getTaxIdentifiers())
                && validateEmail(accounting.getEmail())
                && validateWebsite(accounting.getWebsite());
    }

    private boolean validateCompanyName(String companyName) {
        return companyName.matches("[a-zA-Z0-9 .-]+");
    }

    private boolean validateLegalName(String legalName) {
        return legalName.matches("[a-zA-Z0-9 .-]+");
    }

    private boolean validateAddress(Address address) {
        return address.getLine1().matches("[a-zA-Z0-9\\- ,.]*")
                && address.getLine2().matches("[a-zA-Z0-9\\- ,.]+")
                && address.getCity().matches("[a-zA-Z]+")
                && address.getState().matches("[a-zA-Z]+")
                && address.getZip().matches("[0-9]+")
                && address.getCountry().matches("[a-zA-Z]+");
    }

    private boolean validateTaxIdentifiers(List<TaxIdentifier> taxIdentifiers) {
        for (TaxIdentifier identifier : taxIdentifiers) {
            if (!identifier.getTaxIdentifierType().toString().matches("[A-Z]{3}")
                    || !identifier.getTaxIdentifierNo().matches("[A-Z0-9]{10}")) {
                return false;
            }
        }
        return true;
    }

    private boolean validateEmail(String email) {
        return email.matches("[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9\\-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9\\-]*[a-zA-Z0-9])?");
    }

    private boolean validateWebsite(String website) {
        return website.matches("^(http:\\/\\/|https:\\/\\/)?(www\\.)?([a-zA-Z0-9]+)\\.[a-zA-Z0-9]*\\.[a-z]{3}\\.?([a-z]+)?$");
    }
}
