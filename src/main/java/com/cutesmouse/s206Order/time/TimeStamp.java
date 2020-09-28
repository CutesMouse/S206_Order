package com.cutesmouse.s206Order.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class TimeStamp {
    public final int year; //xxxx年
    public final int month; //x月
    public final int week; //第x週
    public final int day; //星期x
    public TimeStamp(Calendar c) {
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        week = c.get(Calendar.WEEK_OF_MONTH);
        day = c.get(Calendar.DAY_OF_WEEK);
    }
    public boolean isToday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(new Date()).equals(format.format(toDate()));
    }
    public TimeStamp(int year, int month, int weekOfMonth, int dayOfWeek) {
        this.year = year;
        this.month = month;
        this.week = weekOfMonth;
        this.day = dayOfWeek;
    }
    public Date toDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0L);
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.WEEK_OF_MONTH,week);
        cal.set(Calendar.DAY_OF_WEEK,day);
        return new Date(cal.getTimeInMillis());
    }
    public TimeStamp(int month, int weekOfMonth, int dayOfWeek) {
        this(Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR),month,weekOfMonth,dayOfWeek);
    }
    public TimeStamp(int weekOfMonth, int dayOfWeek) {
        this(Calendar.getInstance(TimeZone.getDefault()).get(Calendar.MONTH),weekOfMonth,dayOfWeek);
    }
    public TimeStamp(int dayOfWeek) {
        this(Calendar.getInstance(TimeZone.getDefault()).get(Calendar.WEEK_OF_MONTH),dayOfWeek);
    }
    public TimeStamp() {
        this(Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_WEEK));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeStamp timeStamp = (TimeStamp) o;
        return year == timeStamp.year &&
                month == timeStamp.month &&
                week == timeStamp.week &&
                day == timeStamp.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, week, day);
    }

    @Override
    public String toString() {
        return "TimeStamp{" +
                "year=" + year +
                ", month=" + month +
                ", week=" + week +
                ", day=" + day +
                '}';
    }
}
