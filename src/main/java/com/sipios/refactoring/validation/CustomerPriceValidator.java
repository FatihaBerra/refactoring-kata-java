package com.sipios.refactoring.validation;

import com.sipios.refactoring.enums.CustomerType;
import com.sipios.refactoring.exception.PriceIsTooHighException;
import org.springframework.stereotype.Component;

@Component
public class CustomerPriceValidator {

    private final String STANDARD_CUSTOMER = "Standard";
    private final String PREMUIM_CUSTOMER = "Premium";
    private final String PLATINUIM_CUSTOMER = "Platinum";

    private final double STANDARD_MAX_PRICE = 200;
    private final double PREMUIM_MAX_PRICE = 800;
    private final double PLATINUM_MAX_PRICE = 2000;

    public void verifyPriceNotTooHigh(CustomerType customerType, double price) {
        if (customerType.equals(CustomerType.PREMIUM_CUSTOMER) && price > PREMUIM_MAX_PRICE) {
            throw new PriceIsTooHighException(price, PREMUIM_CUSTOMER);
        } else if (customerType.equals(CustomerType.PLATINUM_CUSTOMER) && price > PLATINUM_MAX_PRICE) {
            throw new PriceIsTooHighException(price, PLATINUIM_CUSTOMER);
        } else if (price > STANDARD_MAX_PRICE) {
            throw new PriceIsTooHighException(price, STANDARD_CUSTOMER);
        }
    }
}
