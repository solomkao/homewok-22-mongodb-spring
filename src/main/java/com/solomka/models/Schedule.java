package com.solomka.models;

public enum Schedule {
    WEEKDAYS("weekdays"),
    WEEKENDS("weekends"),
    EVERYDAY("everyday"),
    ONCE("once");

    private final String value;

    Schedule(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Schedule determineSchedule(final String stringValue) {
        for (Schedule scheduleObject : values()) {
            if (stringValue.equals(scheduleObject.getValue())) {
                return scheduleObject;
            }
        }
        return null;
    }
}
