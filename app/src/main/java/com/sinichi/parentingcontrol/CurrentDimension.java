package com.sinichi.parentingcontrol;

import java.util.Calendar;

public class CurrentDimension {

    Calendar calendar;

    String getDays() {
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.SUNDAY:
                return "Minggu";
                break;
            case Calendar.MONDAY:
                return "Senin";
                break;
            case Calendar.TUESDAY:
                return "Selasa";
                break;
            case Calendar.WEDNESDAY:
                return "Rabu";
                break;
            case Calendar.THURSDAY:
                return "Kamis";
                break;
            case Calendar.FRIDAY:
                return "Jum'at";
                break;
            case Calendar.SATURDAY:
                return "Sabtu";
                break;
        }
        return "Failed to getDays";
    }

}
