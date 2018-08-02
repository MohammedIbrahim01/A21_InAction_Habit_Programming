package com.InAction.X.x21InAction.utils;

import java.util.Calendar;

public class StringFormats {


    /**
     * format Time from calendar
     *
     * @param calendar
     * @return
     */
    public static String formattedTime(Calendar calendar) {

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String formattedTime = "";
        String AM_PM = "";

        if (hour < 12) {

            AM_PM = "am";
        } else {
            hour -= 12;
            AM_PM = "pm";
        }

        formattedTime = hour + " : " + minute + " " + AM_PM;

        return formattedTime;
    }
}
