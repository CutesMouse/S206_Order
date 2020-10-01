package com.cutesmouse.s206Order.form;

import com.cutesmouse.s206Order.restaurant.Meal;
import com.cutesmouse.s206Order.restaurant.MealOrder;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.time.TimeStamp;

public class MealData {
    private Meal m;
    private int amount;
    private Restaurant r;
    private TimeStamp time;
    public MealData(Meal m, int amount, TimeStamp time) {
        this.time = time;
        this.m = m;
        this.amount = amount;
    }

    public MealOrder asMealOrder() {
        return new MealOrder(m,getAmount());
    }

    public int getPrice() {
        return m.price * amount;
    }

    public TimeStamp getTime() {
        return time;
    }

    public Meal getMeal() {
        return m;
    }

    public Restaurant getRestaurant() {
        return r;
    }

    public void setRestaurant(Restaurant r) {
        this.r = r;
    }

    public int getAmount() {
        return amount;
    }
}
