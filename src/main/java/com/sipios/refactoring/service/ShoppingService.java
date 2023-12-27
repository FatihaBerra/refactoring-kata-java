package com.sipios.refactoring.service;

import com.sipios.refactoring.request.GetPriceRequest;
import com.sipios.refactoring.validation.CustomerPriceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class ShoppingService {

    @Autowired
    CustomerPriceValidator customerPriceValidator;
    @Autowired
    PricingService pricingService;
    @Autowired
    CustomerDiscountService customerDiscountService;

    public Double getPrice(GetPriceRequest priceRequest) {

        if (isNull(priceRequest)
            || isNull(priceRequest.getItems())
            || priceRequest.getItems().isEmpty()) {
            return 0.0;
        }

        double discount = customerDiscountService.getCustomerDiscount(priceRequest.getType());

        double price = pricingService.computeTotalPrice(priceRequest.getItems(), discount);

        customerPriceValidator.verifyPriceNotTooHigh(priceRequest.getType(), price);

        return price;
    }
}
