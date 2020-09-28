package com.cutesmouse.s206Order.restaurant;

import java.util.ArrayList;

public class RestaurantBuilder{
    private String restaurantName;
    private String tel;
    public ArrayList<Meal> meals;
    public RestaurantBuilder(String name) {
        restaurantName = name;
        meals = new ArrayList<>();
    }
    public RestaurantBuilder() {
        meals = new ArrayList<>();
    }
    public RestaurantBuilder withName(String name) {
        restaurantName = name;
        return this;
    }
    public RestaurantBuilder withMeal(String name, int price) {
        return withMeal(new Meal(name,price));
    }
    public RestaurantBuilder withMeal(Meal meal) {
        meals.add(meal);
        return this;
    }
    public RestaurantBuilder withTel(String tel) {
        this.tel = tel;
        return this;
    }
    public Restaurant build() {
        return new Restaurant(restaurantName,meals,tel);
    }
}
