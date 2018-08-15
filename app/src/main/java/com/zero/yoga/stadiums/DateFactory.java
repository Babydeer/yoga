package com.zero.yoga.stadiums;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zero on 2018/8/14.
 */

public class DateFactory {

    public static ArrayList<DateBean> getWeekDates() {
        ArrayList<DateBean> result = new ArrayList<>(7);
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        for (int i = 0; i < 7; i++) {
            SimpleDateFormat sFmt1 = new SimpleDateFormat("MM-dd");
            SimpleDateFormat sFmt2 = new SimpleDateFormat("E");
            SimpleDateFormat sFmt3 = new SimpleDateFormat("yyyy-MM-dd");
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            Date datei = calendar.getTime();
            DateBean dateBean = new DateBean(sFmt1.format(datei), sFmt2.format(datei), sFmt3.format(datei), calendar.getTimeInMillis());
            result.add(dateBean);
        }
        return result;
    }

}
