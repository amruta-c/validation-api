package com.intuit.validationapi.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ValidationService {
    public void validate(BusinessProfile businessProfile, String product) throws Exception {
        JSONObject validationConfig = (JSONObject) readValidationConfiguration().get(product);
        Field[] fields = businessProfile.getClass().getDeclaredFields();
        String fieldName;
        for (Field field : fields) {
            fieldName = getFieldName(field);
            try {
                Method getterMethod = businessProfile.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                Object response = getterMethod.invoke(businessProfile);
                if (!configValidation(response, fieldName, validationConfig)) {
                    throw new InvalidDataException("Invalid data for " + fieldName + " according to field configuration it should comply the regExp provided. This validation is by " + product + " QB application");
                }
            } catch (InvalidDataException e) {
                throw new InvalidDataException(e.getMessage());
            } catch (Exception e) {
                throw new ValidationConfigurationException("Error in validation configuration unable to setup configuration for all products. Check validationData.json for more information. " + e.getMessage());
            }
        }
    }
}
