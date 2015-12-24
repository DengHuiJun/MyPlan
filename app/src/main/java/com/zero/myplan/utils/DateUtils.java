package com.zero.myplan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zero on 15-12-23.
 */
public class DateUtils {

    private static final int DAY_MILLS = 24*60*60*1000;

    public static int getDaysByTwoDate(long currentTime, long planTime) {
        return  (int) ((planTime - currentTime) / DAY_MILLS);
    }

    public static String getDateByMillis(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(time);
    }

    public static Date getDate(int year, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date date = null;
        try {
            date = sdf.parse(year+ "-" + (month+1) + "-" + day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

}
