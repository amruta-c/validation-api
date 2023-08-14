package com.intuit.validationapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxIdentifier {
    private TaxIdentifierType taxIdentifierType;
    private String taxIdentifierNo;
}
