package com.solomka.exceptions;

public class IllegalScheduleException extends RuntimeException {

    private static String message = "Invalid schedule. Must be weekdays, weekends, everyday or once";

    public IllegalScheduleException(){
        super(message);
    }
}
