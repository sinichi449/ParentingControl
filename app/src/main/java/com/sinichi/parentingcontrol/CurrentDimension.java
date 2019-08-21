package com.sinichi.parentingcontrol;

import java.util.Calendar;

public class CurrentDimension {

    Calendar calendar;

    String getDays() {
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String hari = "";
        switch (day) {
            case Calendar.SUNDAY:
                hari = "Minggu";
                break;
            case Calendar.MONDAY:
                hari = "Senin";
                break;
            case Calendar.TUESDAY:
                hari = "Selasa";
                break;
            case Calendar.WEDNESDAY:
                hari =  "Rabu";
                break;
            case Calendar.THURSDAY:
                hari = "Kamis";
                break;
            case Calendar.FRIDAY:
                hari = "Jum'at";
                break;
            case Calendar.SATURDAY:
                hari = "Sabtu";
                break;
        }
        return hari;
    }

    String getDate() {
        calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        return String.valueOf(date);
    }

    String getMonth() {
        calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        return String.valueOf(month);
    }

    String getYear() {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return String.valueOf(year);
    }

}
