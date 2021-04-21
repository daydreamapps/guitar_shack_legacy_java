package com.guitarshack;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DateRangeTest {

    @Test
    public void startDateOneYearPriorToInput() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);

        Calendar expectedStart = Calendar.getInstance();
        expectedStart.set(1999, Calendar.JANUARY, 1, 0, 0, 0);

        assertTrue(assertWithinMillis(new DateRange(calendar).getStartDate(), expectedStart.getTime(), 1000));
    }

    @Test
    public void endDate30DaysAfterStartDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);

        Calendar expectedEnd = Calendar.getInstance();
        expectedEnd.set(1999, Calendar.JANUARY, 31, 0, 0, 0);

        assertTrue(assertWithinMillis(new DateRange(calendar).getEndDate(), expectedEnd.getTime(), 1000));
    }

    private Boolean assertWithinMillis(Date first, Date second, long threshold) {
        return first.getTime() - second.getTime() < threshold;
    }
}