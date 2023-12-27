package com.sipios.refactoring.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sipios.refactoring.exception.UnknownCustomerType;

public enum CustomerType {
    STANDARD_CUSTOMER,
    PREMIUM_CUSTOMER,
    PLATINUM_CUSTOMER;

    @JsonCreator
    public static CustomerType fromValue(String value) {
        for (CustomerType customerType : values()) {
            if (customerType.name().equalsIgnoreCase(value)) {
                return customerType;
            }
        }
        throw new UnknownCustomerType("Unknown customer type: " + value);
    }
}
