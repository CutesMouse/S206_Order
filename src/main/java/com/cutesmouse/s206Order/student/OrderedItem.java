package com.cutesmouse.s206Order.student;

import com.cutesmouse.s206Order.restaurant.MealOrder;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.time.TimeStamp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class OrderedItem implements Serializable {
    public final long PLACETIME;
    public TimeStamp timeStamp;
    public Restaurant restaurant;
    public MealOrder ordered;
    public int Totalprice;
    public Student student;
    public OrderedItem(Restaurant restaurant, MealOrder ordered, TimeStamp time) {
        Totalprice = 0;
        Totalprice += ordered.meal.price * ordered.amount;
        this.timeStamp = time;
        this.ordered = ordered;
        this.restaurant = restaurant;
        PLACETIME = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return student.nid+"/"+restaurant.name+"/"+ordered.meal.name+"($"+ordered.meal.price+") x"+ordered.amount +" 共 "+Totalprice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedItem that = (OrderedItem) o;
        return restaurant.equals(that.restaurant) && ordered.equals(that.ordered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PLACETIME, timeStamp, restaurant, ordered, Totalprice, student);
    }
}
