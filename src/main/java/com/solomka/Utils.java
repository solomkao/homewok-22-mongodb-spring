package com.solomka;

import com.solomka.exceptions.IllegalScheduleException;
import com.solomka.models.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public final class Utils {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final Random RANDOM = new Random();

    private Utils(){}

    public static Date convertStringToDate(String value) throws ParseException {
        return FORMAT.parse(value);
    }

    public static String convertDateToString(Date date) {
        return FORMAT.format(date);
    }

    public static Schedule convertStringToSchedule(String value){
        value = value.toLowerCase().trim();
        Schedule schedule = Schedule.determineSchedule(value);
        if(schedule == null){
            throw new IllegalScheduleException();
        }
        return schedule;
    }

    public static String getRandomNameAlarm(){
        List<String> randomWords = List.of("employ",
                "cactus",
                "clip",
                "check",
                "disgusted",
                "shrill",
                "unsuitable",
                "industrious",
                "sprout",
                "ludicrous",
                "skillful",
                "pocket");
        return randomWords.get(RANDOM.nextInt(randomWords.size()));
    }
}
