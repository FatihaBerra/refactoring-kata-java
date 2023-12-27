package com.sipios.refactoring.service;

import com.sipios.refactoring.enums.ItemType;
import com.sipios.refactoring.request.ItemRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.sipios.refactoring.helper.SalesDateHelper.isSummerSalesSeason;
import static com.sipios.refactoring.helper.SalesDateHelper.isWinterSalesSeason;

@Service
public class PricingService {

    final private double TSHIRT_PRICE = 30;
    final private double DRESS_PRICE = 50;
    final private double JACKET_PRICE = 30;
    final private double DRESS_DISCOUNT = 0.8;
    final private double JACKET_DISCOUNT = 0.9;

    public double computeTotalPrice(List<ItemRequest> items, double discount) {
        boolean isSalesSeason = isSummerSalesSeason() || isWinterSalesSeason();
        return computeTotalDiscountAmount(items, discount, isSalesSeason);
    }

    public double computeTotalDiscountAmount(List<ItemRequest> items, double customerDiscount, boolean isSalesSeason) {
        return items.stream()
            .mapToDouble(item -> computeItemPrice(item, customerDiscount, isSalesSeason)).sum();
    }

    private double computeItemPrice(ItemRequest item,
                                    double customerDiscount,
                                    boolean isSalesSeason) {
        final double dressDiscount = isSalesSeason ? DRESS_DISCOUNT : 1;
        final double jacketDiscount = isSalesSeason ? JACKET_DISCOUNT : 1;

        if (ItemType.TSHIRT.equals(item.getType())) {
            return TSHIRT_PRICE * item.getNb() * customerDiscount;
        } else if (ItemType.DRESS.equals(item.getType())) {
            return DRESS_PRICE * item.getNb() * dressDiscount * customerDiscount;
        } else if (ItemType.JACKET.equals(item.getType())) {
            return JACKET_PRICE * item.getNb() * jacketDiscount * customerDiscount;
        }

        return 0;
    }

}
