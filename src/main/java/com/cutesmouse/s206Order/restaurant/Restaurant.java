package com.cutesmouse.s206Order.restaurant;

import com.cutesmouse.s206Order.form.FormInfo;
import com.cutesmouse.s206Order.time.TimeStamp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Restaurant implements Serializable {
    public static List<Restaurant> RESTAURANTS;
    private final long IDENTIFY;
    public static void removeRestaurant(Restaurant r) {
        RESTAURANTS.remove(r);
        ArrayList<TimeStamp> tps = new ArrayList<>(FormInfo.FORM_INFOS.keySet());
        for (TimeStamp tp : tps) {
            FormInfo info = FormInfo.FORM_INFOS.get(tp);
            info.removeRestaurant(r);
            if (info.getRestaurants().size() == 0) FormInfo.FORM_INFOS.remove(tp);
        }
    }
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
        IDENTIFY = System.currentTimeMillis();
        RESTAURANTS.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return IDENTIFY == that.IDENTIFY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDENTIFY, name, meals, tel);
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
