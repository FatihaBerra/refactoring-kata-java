package com.sipios.refactoring.service;

import com.sipios.refactoring.enums.CustomerType;
import org.springframework.stereotype.Service;

@Service
public class CustomerDiscountService {

    final private double DEFAULT_DISCOUNT = 1;

    final private double STANDARD_CUSTOMER_DISCOUNT = 1;
    final private double PREMIUM_CUSTOMER_DISCOUNT = 0.9;
    final private double PLATINUM_CUSTOMER_DISCOUNT = 0.5;

    public double computeCustomerDiscount(CustomerType customerType) {
        if (customerType.equals(CustomerType.STANDARD_CUSTOMER)) {
            return STANDARD_CUSTOMER_DISCOUNT;
        } else if (customerType.equals(CustomerType.PREMIUM_CUSTOMER)) {
            return PREMIUM_CUSTOMER_DISCOUNT;
        } else if (customerType.equals(CustomerType.PLATINUM_CUSTOMER)) {
            return PLATINUM_CUSTOMER_DISCOUNT;
        }

        return DEFAULT_DISCOUNT;
    }
}
