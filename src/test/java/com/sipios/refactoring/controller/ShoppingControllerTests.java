package com.sipios.refactoring.controller;

import com.sipios.refactoring.UnitTest;
import com.sipios.refactoring.enums.CustomerType;
import com.sipios.refactoring.request.GetPriceRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Collections;

class ShoppingControllerTests extends UnitTest {

    @InjectMocks
    private ShoppingController controller;

    @Test
    void should_not_throw() {

        //mock ShoppingService
        Assertions.assertDoesNotThrow(
            () -> controller.getPrice(new GetPriceRequest(Collections.emptyList(), CustomerType.STANDARD_CUSTOMER))
        );
    }
}
