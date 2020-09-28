package com.cutesmouse.s206Order.utils;

import com.cutesmouse.s206Order.student.OrderedItem;
import com.cutesmouse.s206Order.student.Student;
import com.cutesmouse.s206Order.time.TimeStamp;

import java.util.ArrayList;

public class DayOrderedManager {
    public static ArrayList<OrderedItem> getAllOrderedOn(TimeStamp time) {
        ArrayList<OrderedItem> items = new ArrayList<>();
        for (Student st : Student.STUDENTS) {
            for (OrderedItem or : st.ORDERED) {
                if (or.timeStamp.equals(time)) items.add(or);
            }
        }
        return items;
    }
}
