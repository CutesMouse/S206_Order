package com.cutesmouse.s206Order.restaurant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable {
    public static List<Restaurant> RESTAURANTS;
    public static void register(Restaurant r) {
        RESTAURANTS.add(r);
    }
    public String name;
    public ArrayList<Meal> meals;
    public String tel;
    protected Restaurant(String name, ArrayList<Meal> meals, String tel) {
        this.name = name;
        this.meals = meals;
        this.tel = tel;
        RESTAURANTS.add(this);
    }

    @Override
    public String toString() {
        int max = 15;
        StringBuilder sb = new StringBuilder();
        int current = 0;
        boolean first = true;
        boolean ends = false;
        for (Meal m : meals) {
            if (!first) {
                if (current <= max) sb.append(",");
                current++;
            }
            first = false;
            if (current >= max) {
                ends = true;
                break;
            }
            String name = m.name;
            for (char c : (name.toCharArray())) {
                if (current >= max) {
                    ends = true;
                    break;
                }
                sb.append(c);
                current++;
            }
        }
        return name+"/"+tel+"/"+sb.toString()+(ends ? "..." : "");
    }
}
