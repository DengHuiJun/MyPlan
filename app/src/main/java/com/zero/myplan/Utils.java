package com.zero.myplan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zero on 15-12-23.
 */
public class Utils {

    public static long getDaysByTwoDate(int fy, int fm, int fd, int ty, int tm, int td) {
        long days = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date datef = sdf.parse(fy+"-"+fm+"-"+fd);
            Date datet = sdf.parse(ty+"-"+tm+"-"+td);
            days = (datet.getTime()-datef.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }



}
