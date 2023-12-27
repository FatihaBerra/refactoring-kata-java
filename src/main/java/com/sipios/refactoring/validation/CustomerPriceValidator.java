package com.sipios.refactoring.validation;

import com.sipios.refactoring.exception.PriceIsTooHighException;
import org.springframework.stereotype.Component;

@Component
public class CustomerPriceValidator {

    public void verifyPriceNotTooHigh(String type, double price) {
        if (type.equals("PREMIUM_CUSTOMER") && price > 800) {
            throw new PriceIsTooHighException(price, "premium");
        } else if (type.equals("PLATINUM_CUSTOMER") && price > 2000) {
            throw new PriceIsTooHighException(price, "platinum");
        } else if (price > 200) {
            throw new PriceIsTooHighException(price, "standard");
        }
    }
}
