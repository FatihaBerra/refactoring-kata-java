package com.sipios.refactoring.service;

import com.sipios.refactoring.enums.ItemType;
import com.sipios.refactoring.request.ItemRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class PricingService {

    final private double TSHIRT_PRICE = 30;
    final private double DRESS_PRICE = 50;
    final private double JACKET_PRICE = 30;
    final private double DRESS_DISCOUNT = 0.8;
    final private double JACKET_DISCOUNT = 0.9;

    final private LocalDate NOW = LocalDate.now(ZoneId.of("Europe/Paris"));
    final private LocalDate WINTER_SALES_START_DATE = LocalDate.of(NOW.getYear(), 1, 5);
    final private LocalDate WINTER_SALES_END_DATE = LocalDate.of(NOW.getYear(), 1, 15);

    final private LocalDate SUMMER_SALES_START_DATE = LocalDate.of(NOW.getYear(), 6, 5);

    final private LocalDate SUMMER_SALES_END_DATE = LocalDate.of(NOW.getYear(), 6, 15);


    public double computeTotalPrice(List<ItemRequest> items, double discount) {
        if (isDiscountSummerSeason() || isDiscountWinterSeason()) {
            return computeTotalDiscountAmount(items, discount, true);
        } else {
            return computeTotalDiscountAmount(items, discount, false);
        }
    }

    public double computeTotalDiscountAmount(List<ItemRequest> items, double customerDiscount, boolean isSalesSeason) {
        return items.stream()
            .mapToDouble(item -> calculateItemPrice(item, customerDiscount, isSalesSeason)).sum();
    }

    private double calculateItemPrice(ItemRequest item, double customerDiscount, boolean isSalesSeason) {
        double dressDiscount = isSalesSeason ? DRESS_DISCOUNT : 1;
        double jacketDiscount = isSalesSeason ? JACKET_DISCOUNT : 1;

        if (item.getType().equals(ItemType.TSHIRT)) {
            return TSHIRT_PRICE * item.getNb() * customerDiscount;
        } else if (item.getType().equals(ItemType.DRESS)) {
            return DRESS_PRICE * item.getNb() * dressDiscount * customerDiscount;
        } else if (item.getType().equals(ItemType.JACKET)) {
            return JACKET_PRICE * item.getNb() * jacketDiscount * customerDiscount;
        }

        return 0;
    }

    private boolean isDiscountWinterSeason() {
        return NOW.isAfter(WINTER_SALES_START_DATE) && NOW.isBefore(WINTER_SALES_END_DATE);
    }

    private boolean isDiscountSummerSeason() {
        return NOW.isAfter(SUMMER_SALES_START_DATE) && NOW.isBefore(SUMMER_SALES_END_DATE);
    }
}
