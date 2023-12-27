package com.sipios.refactoring.controller;

import com.sipios.refactoring.request.GetPriceRequest;
import com.sipios.refactoring.service.ShoppingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/shopping_v2")
public class ShoppingControllerV2 {

    private Logger logger = LoggerFactory.getLogger(ShoppingControllerV2.class);

    @Autowired
    private ShoppingService shoppingService;

    @PostMapping
    public String getPrice(@RequestBody GetPriceRequest priceRequest) {
        return String.valueOf(shoppingService.getPrice(priceRequest));
    }
}
