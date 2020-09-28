package com.cutesmouse.s206Order.restaurant;

import java.io.Serializable;

public class MealOrder implements Serializable {
    public Meal meal;
    public int amount;
    public MealOrder(Meal m, int amount) {
        meal = m;
        this.amount = amount;
    }
}
