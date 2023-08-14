package com.intuit.validationapi.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BusinessProfile {
    private String companyName;
    private String legalName;
    private Address businessAddress;
    private Address legalAddress;
    private List<TaxIdentifier> taxIdentifiers;
    private String email;
    private String website;
    private String product;
}
