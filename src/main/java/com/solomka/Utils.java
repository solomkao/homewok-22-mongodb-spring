package com.solomka;

import com.solomka.models.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class Utils {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Utils(){}

    public static Date convertStringToDate(String value) throws ParseException {
        return format.parse(value);
    }

    public static String convertDateToString(Date date) {
        return format.format(date);
    }

    public static Schedule convertStringToSchedule(String value){
        value = value.toLowerCase().trim();
        Schedule schedule = Schedule.determineSchedule(value);
        if(schedule == null){
            throw new IllegalArgumentException();
        }
        return schedule;
    }
}
