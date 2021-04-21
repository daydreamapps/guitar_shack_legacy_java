package com.guitarshack;

import java.util.Calendar;
import java.util.Date;

public class ReorderThreshold {

    private final SalesHistory salesHistory;

    public ReorderThreshold(SalesHistory salesHistory) {
        this.salesHistory = salesHistory;
    }

    int getForProduct(Product product) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());

        Date endDate = calendar.getTime();
        calendar.add(Calendar.DATE, -30);

        Date startDate = calendar.getTime();
        SalesTotal total = salesHistory.getSalesTotal(product, startDate, endDate);

        return (int) ((double) (total.getTotal() / 30) * product.getLeadTime());
    }
}