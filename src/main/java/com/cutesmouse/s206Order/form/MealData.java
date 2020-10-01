package com.cutesmouse.s206Order.form;

import com.cutesmouse.s206Order.restaurant.Meal;
import com.cutesmouse.s206Order.restaurant.Restaurant;

public class MealData {
    private Meal m;
    private int amount;
    private Restaurant r;
    public MealData(Meal m, int amount) {
        this.m = m;
        this.amount = amount;
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
