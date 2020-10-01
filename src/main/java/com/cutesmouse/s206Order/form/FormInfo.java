package com.cutesmouse.s206Order.form;

import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.student.OrderedItem;
import com.cutesmouse.s206Order.student.Student;
import com.cutesmouse.s206Order.time.TimeStamp;
import com.cutesmouse.s206Order.utils.DayOrderedManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class FormInfo implements Serializable {
    public static HashMap<TimeStamp,FormInfo> FORM_INFOS;
    private ArrayList<Restaurant> avaliablePool;
    private TimeStamp time;
    private boolean toggled;
    public FormInfo(TimeStamp time, ArrayList<Restaurant> restaurants) {
        this.avaliablePool = restaurants;
        this.time = time;
        this.toggled = false;
        FORM_INFOS.put(time,this);
    }

    public TimeStamp getTime() {
        return time;
    }

    public boolean isToggled() {
        if (!time.isToday() && time.toDate().getTime() < System.currentTimeMillis()) return false;
        return toggled;
    }

    public void setToggled(boolean b) {
        this.toggled = b;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return avaliablePool;
    }

    public void appendRestaurant(Restaurant r) {
        avaliablePool.add(r);
    }
    public void removeRestaurant(Restaurant r) {
        avaliablePool.remove(r);
    }
    public void setRestaurants(ArrayList<Restaurant> r) {
        this.avaliablePool = r;
    }
    public ArrayList<OrderedItem> getResult() {
        return DayOrderedManager.getAllOrderedOn(time);
    }
    public void submit(Student student) {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof FormInfo)) return false;
        return ((FormInfo) o).time.equals(time);
    }
}
