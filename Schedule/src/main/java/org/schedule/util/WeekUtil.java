package org.schedule.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.schedule.entity.Weekday;

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
    
    public static String getFirstDayOfLastWeek(Date date, int dayNumber) throws ParseException {
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 7);

        return threadLocal.get().format(c.getTime());
    }

    public static String getLastDayOfLastWeek(Date date, int dayNumber) throws ParseException {
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day -1 );

        return threadLocal.get().format(c.getTime());
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
    
    public static String getFirstDayOfNextWeek(Date date, int dayNumber) throws ParseException {
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 7);

        return threadLocal.get().format(c.getTime());
    }

    public static String getLastDayOfNextWeek(Date date, int dayNumber) throws ParseException {
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 13 );

        return threadLocal.get().format(c.getTime());
    }
    
    public  static List<Weekday> getAllWeek(Date date, int dayNumber) throws ParseException {
        Calendar c = Calendar.getInstance();

        List<Weekday> list = new ArrayList<Weekday>();
        date = threadLocal.get().parse(getFirstDayOfCurrentWeek(date, dayNumber));
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        
        list.add(new Weekday(1, date, getWeek(date)));
        int tmpDay = day;
        for (int i = 1; i <= 6; i++) {
            c.set(Calendar.DATE, tmpDay + 1);
            tmpDay= c.get(Calendar.DATE);
            Date datetmp = threadLocal.get().parse(threadLocal.get().format(c.getTime()));
            list.add(new Weekday(getWeekNumber(datetmp), datetmp, getWeek(datetmp)));
        }

        return list;
    }
    
    public  static String[] getAllWeekString(Date date, int dayNumber) throws ParseException {
        Calendar c = Calendar.getInstance();
        String[] tmp = new String[7];
        date = threadLocal.get().parse(getFirstDayOfCurrentWeek(date, dayNumber));
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        
        tmp[0] = getWeek(date) + "<br/><br/>" + threadLocal.get().format(date);
        int tmpDay = day;
        for (int i = 1; i <= 6; i++) {
            c.set(Calendar.DATE, tmpDay + 1);
            tmpDay= c.get(Calendar.DATE);
            Date datetmp = threadLocal.get().parse(threadLocal.get().format(c.getTime()));
            tmp[i] = threadLocal.get().format(datetmp) + "(" + getWeek(datetmp) + ")";
        }

        return tmp;
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
