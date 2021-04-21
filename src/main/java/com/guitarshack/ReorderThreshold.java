package com.guitarshack;

import java.util.Calendar;

public class ReorderThreshold {

    private final SalesHistory salesHistory;

    public ReorderThreshold(SalesHistory salesHistory) {
        this.salesHistory = salesHistory;
    }

    int getForProduct(Product product) {
        DateRange dateRange = new DateRange(Calendar.getInstance());
        SalesTotal total = salesHistory.getSalesTotal(product, dateRange.getStartDate(), dateRange.getEndDate());

        return (int) ((double) (total.getTotal() / 30) * product.getLeadTime());
    }

}