package com.sipios.refactoring.service;

import com.sipios.refactoring.request.GetPriceRequest;
import com.sipios.refactoring.validation.CustomerPriceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class ShoppingService {

    @Autowired
    CustomerPriceValidator customerPriceValidator;
    @Autowired
    PricingService pricingService;
    @Autowired
    CustomerDiscountService customerDiscountService;

    public Double getPrice(GetPriceRequest priceRequest) {

        if (priceRequest.getItems() == null || priceRequest.getItems().isEmpty()) {
            return 0.0;
        }

        double discount = customerDiscountService.computeCustomerDiscount(priceRequest.getType());

        double price = pricingService.computeTotalPrice(priceRequest.getItems(), discount);

        try {
            customerPriceValidator.verifyPriceNotTooHigh(priceRequest.getType(), price);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return price;
    }
}
