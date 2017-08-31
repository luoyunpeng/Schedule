package org.schedule.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeekUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
    
    public static String getWeek(Date date){ 
        String week = sdf.format(date);
        return week;
    }
}
