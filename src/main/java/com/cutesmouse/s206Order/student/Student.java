package com.cutesmouse.s206Order.student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    public static List<Student> STUDENTS;
    public static Student getStudent(int nid, String sid) {
        for (Student s : STUDENTS) {
            if (s.nid == nid && s.sid.equals(sid)) return s;
        }
        return new Student(nid,sid);
    }
    public static void register(Student st) {
        STUDENTS.add(st);
    }
    public int nid;
    public String sid;
    private int money;
    public ArrayList<OrderedItem> ORDERED;
    public Student(int nid, String sid) {
        this.nid = nid;
        this.sid = sid;
        this.money = 0;
        ORDERED = new ArrayList<>();
        STUDENTS.add(this);
    }
    public void Order(OrderedItem order) {
        ORDERED.add(order);
        order.student = this;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void withdraw(int value) {
        money -= value;
    }

    public void deposit(int value) {
        money += value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return nid == student.nid && sid.equals(student.sid);
    }
}
