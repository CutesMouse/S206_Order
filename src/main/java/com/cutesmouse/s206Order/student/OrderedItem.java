package com.cutesmouse.s206Order.student;

import com.cutesmouse.s206Order.restaurant.MealOrder;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.time.TimeStamp;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderedItem implements Serializable {
    public final long PLACETIME;
    public TimeStamp timeStamp;
    public Restaurant restaurant;
    public ArrayList<MealOrder> ordered;
    public int Totalprice;
    public Student student;
    public OrderedItem(Restaurant restaurant, ArrayList<MealOrder> ordered, TimeStamp time) {
        Totalprice = 0;
        for (MealOrder order : ordered) {
            Totalprice += order.meal.price * order.amount;
        }
        this.timeStamp = time;
        this.ordered = ordered;
        this.restaurant = restaurant;
        PLACETIME = System.currentTimeMillis();
    }
}
