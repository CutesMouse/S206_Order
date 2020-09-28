package com.cutesmouse.s206Order.restaurant;

import java.io.Serializable;

public class Meal implements Serializable {
    public String name;
    public int price;
    public Meal(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name+"/"+price+"$";
    }
}
