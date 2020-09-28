package com.cutesmouse.s206Order.student;

import com.cutesmouse.s206Order.time.TimeStamp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    public static List<Student> STUDENTS;
    public static void register(Student st) {
        STUDENTS.add(st);
    }
    public int number;
    public int money;
    public ArrayList<OrderedItem> ORDERED;
    public Student(int num) {
        this.number = num;
        this.money = 0;
        ORDERED = new ArrayList<>();
        STUDENTS.add(this);
    }
    public void Order(OrderedItem order) {
        ORDERED.add(order);
        order.student = this;
    }
}
