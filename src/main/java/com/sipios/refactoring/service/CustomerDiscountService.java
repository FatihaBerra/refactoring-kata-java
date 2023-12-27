package com.sipios.refactoring.service;

import org.springframework.stereotype.Service;

@Service
public class CustomerDiscountService {
    public double computeCustomerDiscount(String type) {

        if (type.equals("STANDARD_CUSTOMER")) {
            return 1;
        } else if (type.equals("PREMIUM_CUSTOMER")) {
            return 0.9;
        } else if (type.equals("PLATINUM_CUSTOMER")) {
            return 0.5;
        } else {
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // à retirer
        return 1;
    }
}
