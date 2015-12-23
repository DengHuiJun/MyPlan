package com.zero.myplan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zero on 15-12-23.
 */
public class Utils {

    public static int getDaysByTwoDate(int fy, int fm, int fd, int ty, int tm, int td) {
        int days = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date datef = sdf.parse(fy+"-"+fm+"-"+fd);
            Date datet = sdf.parse(ty+"-"+tm+"-"+td);
            days = (int)(datet.getTime()-datef.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public static int[] dateToArray(String date) {
        int[] a = new int[3];
        String[] str = date.split("-");
        a[0] = Integer.parseInt(str[0]);
        a[1] = Integer.parseInt(str[1]);
        a[2] = Integer.parseInt(str[2]);
        return a;
    }

    public static int getDaysByTwoDate(String dateF, String dateT) {
        int[] af = dateToArray(dateF);
        int[] at = dateToArray(dateT);
        return getDaysByTwoDate(af[0], af[1], af[2], at[0], at[1], at[2]);
    }



}
