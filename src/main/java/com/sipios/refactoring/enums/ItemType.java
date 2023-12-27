package com.sipios.refactoring.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sipios.refactoring.exception.UnknownCustomerType;

public enum ItemType {
    TSHIRT,
    DRESS,
    JACKET;

    @JsonCreator
    public static ItemType fromValue(String value) {
        for (ItemType customerType : values()) {
            if (customerType.name().equalsIgnoreCase(value)) {
                return customerType;
            }
        }
        throw new IllegalArgumentException("Unknown item type: " + value);
    }
}
