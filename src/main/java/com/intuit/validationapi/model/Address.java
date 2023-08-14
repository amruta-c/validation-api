package com.intuit.validationapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zip;
    private String country;
}
