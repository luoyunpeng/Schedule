package org.schedule.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeekUtil {

    private static int[] week = { 7, 1, 2, 3, 4, 5, 6 };

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    private static ThreadLocal<DateFormat> threadLocalWeek = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("EEEE");
        }
    };

    public static Date parse(String dateStr) throws ParseException {

        return threadLocal.get().parse(dateStr);
    }

    public static String format(Date date) {

        return threadLocal.get().format(date);
    }

    public static String getWeek(Date date) {

        return threadLocalWeek.get().format(date);
    }

    public static String getFirstDayOfCurrentWeek(Date date, int dayNumber) throws ParseException {
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - (dayNumber - 1));

        return threadLocal.get().format(c.getTime());
    }

    public static String getLastDayOfCurrentWeek(Date date, int dayNumber) throws ParseException {
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + (7 - dayNumber));

        return threadLocal.get().format(c.getTime());
    }

    public static int getWeekNumber(Date date) {
        if (date == null) {
            return 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }

        return week[w];
    }
}
