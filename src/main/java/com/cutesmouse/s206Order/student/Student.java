package com.cutesmouse.s206Order.student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    public static List<Student> STUDENTS;
    public static Student getStudent(int nid, int sid) {
        for (Student s : STUDENTS) {
            if (s.nid == nid && s.sid == sid) return s;
        }
        return new Student(nid,sid);
    }
    public static void register(Student st) {
        STUDENTS.add(st);
    }
    public int nid;
    public int sid;
    private int money;
    public ArrayList<OrderedItem> ORDERED;
    public Student(int nid, int sid) {
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
        return nid == student.nid && sid == student.sid;
    }
}
