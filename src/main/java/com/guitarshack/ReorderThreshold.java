package com.guitarshack;

import java.util.Calendar;
import java.util.Date;

public class ReorderThreshold {

    int getForProduct(Product product, HttpService httpService) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());

        Date endDate = calendar.getTime();
        calendar.add(Calendar.DATE, -30);

        Date startDate = calendar.getTime();
        SalesTotal total = new SalesHistory(httpService).getSalesTotal(product, startDate, endDate);

        return (int) ((double) (total.getTotal() / 30) * product.getLeadTime());
    }
}