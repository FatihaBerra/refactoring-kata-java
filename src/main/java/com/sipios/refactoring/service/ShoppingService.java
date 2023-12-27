package com.sipios.refactoring.service;

import com.sipios.refactoring.request.GetPriceRequest;
import com.sipios.refactoring.request.ItemRequest;
import com.sipios.refactoring.validation.CustomerPriceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class ShoppingService {

    @Autowired
    CustomerPriceValidator customerPriceValidator;

    public String getPrice(GetPriceRequest priceRequest) {

        if (priceRequest.getItems() == null) {
            return "0";
        }

        double price = 0;
        double discount;

        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        calendar.setTime(date);

        discount = computeCustomerDiscount(priceRequest.getType());

        if (!isDiscountSummerSeason(calendar) && !isDiscountWinterSeason(calendar)) {
            price = computeTotalAmountForNormalSeason(priceRequest.getItems(), discount);
        } else {
            price = computeTotalAmountForDiscountSeason(priceRequest.getItems(), discount);
        }

        try {
            customerPriceValidator.verifyPriceNotTooHigh(priceRequest.getType(), price);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return String.valueOf(price);
    }

    private double computeTotalAmountForDiscountSeason(ItemRequest[] items, double discount) {
        double price = 0;

        for (int i = 0; i < items.length; i++) {
            ItemRequest it = items[i];

            if (it.getType().equals("TSHIRT")) {
                price += 30 * it.getNb() * discount;
            } else if (it.getType().equals("DRESS")) {
                price += 50 * it.getNb() * 0.8 * discount;
            } else if (it.getType().equals("JACKET")) {
                price += 100 * it.getNb() * 0.9 * discount;
            }
        }
        return price;
    }

    private double computeTotalAmountForNormalSeason(ItemRequest[] items, double discount) {
        double price = 0;

        for (int i = 0; i < items.length; i++) {
            ItemRequest it = items[i];

            if (it.getType().equals("TSHIRT")) {
                price += 30 * it.getNb() * discount;
            } else if (it.getType().equals("DRESS")) {
                price += 50 * it.getNb() * discount;
            } else if (it.getType().equals("JACKET")) {
                price += 100 * it.getNb() * discount;
            }
        }
        return price;
    }

    private boolean isDiscountWinterSeason(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH) < 15 &&
            calendar.get(Calendar.DAY_OF_MONTH) > 5 &&
            calendar.get(Calendar.MONTH) == 0;
    }

    private boolean isDiscountSummerSeason(Calendar calendar) {
        return
            calendar.get(Calendar.DAY_OF_MONTH) < 15 &&
                calendar.get(Calendar.DAY_OF_MONTH) > 5 &&
                calendar.get(Calendar.MONTH) == 5;
    }

    private double computeCustomerDiscount(String type) {

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
        return 0;
    }
}
