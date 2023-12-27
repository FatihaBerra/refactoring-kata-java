package com.sipios.refactoring.helper;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

public class SalesDateHelper {
    final static private LocalDate NOW = LocalDate.now(ZoneId.of("Europe/Paris"));
    final static private LocalDate WINTER_SALES_START_DATE = LocalDate.of(NOW.getYear(), 1, 5);
    final static private LocalDate WINTER_SALES_END_DATE = LocalDate.of(NOW.getYear(), 1, 15);

    final static private LocalDate SUMMER_SALES_START_DATE = LocalDate.of(NOW.getYear(), 6, 5);

    final static private LocalDate SUMMER_SALES_END_DATE = LocalDate.of(NOW.getYear(), 6, 15);

    public static boolean isWinterSalesSeason() {
        return NOW.isAfter(WINTER_SALES_START_DATE) && NOW.isBefore(WINTER_SALES_END_DATE);
    }

    public static boolean isSummerSalesSeason() {
        return NOW.isAfter(SUMMER_SALES_START_DATE) && NOW.isBefore(SUMMER_SALES_END_DATE);
    }
}
