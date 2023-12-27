package com.sipios.refactoring.validation;

import com.sipios.refactoring.enums.CustomerType;
import com.sipios.refactoring.exception.PriceIsTooHighException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CustomerPriceValidatorTest {

    @InjectMocks
    private CustomerPriceValidator validator;

    @Test
    public void verifyPriceForStandardCustomer() {
        assertDoesNotThrow(() -> validator.verifyPriceNotTooHigh(CustomerType.STANDARD_CUSTOMER, 150));
        assertThrows(PriceIsTooHighException.class,
                     () -> validator.verifyPriceNotTooHigh(CustomerType.STANDARD_CUSTOMER, 250));
    }

    @Test
    public void verifyPriceForPremiumCustomer() {
        assertDoesNotThrow(() -> validator.verifyPriceNotTooHigh(CustomerType.PREMIUM_CUSTOMER, 500));
        assertThrows(PriceIsTooHighException.class,
                     () -> validator.verifyPriceNotTooHigh(CustomerType.PREMIUM_CUSTOMER, 900));
    }

    @Test
    public void verifyPriceForPlatinumCustomer() {
        assertDoesNotThrow(() -> validator.verifyPriceNotTooHigh(CustomerType.PLATINUM_CUSTOMER, 1500));
        assertThrows(PriceIsTooHighException.class,
                     () -> validator.verifyPriceNotTooHigh(CustomerType.PLATINUM_CUSTOMER, 2100));
    }
}
