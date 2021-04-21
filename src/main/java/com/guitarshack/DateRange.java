package com.guitarshack;

import java.util.Calendar;
import java.util.Date;

public class DateRange {

    private final Date startDate;
    private final Date endDate;

    public DateRange(Calendar calendar) {
        calendar.add(Calendar.YEAR, -1);
        startDate = calendar.getTime();

        endDate = calendar.getTime();
        calendar.add(Calendar.DATE, -30);

    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}