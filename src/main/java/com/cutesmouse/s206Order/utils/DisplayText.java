package com.cutesmouse.s206Order.utils;

public class DisplayText {
    public static String DAYOFWEEK(int w) {
        switch (w) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
        }
        return "未知";
    }
}
