package com.cutesmouse.s206Order;

import com.cutesmouse.s206Order.config.Config;
import com.cutesmouse.s206Order.form.FormInfo;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.restaurant.RestaurantBuilder;
import com.cutesmouse.s206Order.student.Student;
import com.cutesmouse.s206Order.time.TimeStamp;
import com.cutesmouse.s206Order.window.OrderWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static Config CONFIG;
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        CONFIG = new Config(Config.getConfigFile("data"));
        loadRestaurants();
        loadStudents();
        loadFormInfo();
        debug();
        new OrderWindow().setVisible(true);
    }
    private static void debug() {
        if (Restaurant.RESTAURANTS.size() == 0) new RestaurantBuilder("有春茶館").
                withTel("0933778701").withMeal("烏龍春茶",65)
                .withMeal("烏龍春奶茶",75).withMeal("芋泥波波奶綠",85)
                .withMeal("荔枝花蜜茶",80).withMeal("有春紅茶",65).build();
    }
    public static void loadFormInfo() {
        HashMap<TimeStamp,FormInfo> form = CONFIG.getObject("FormInfo", HashMap.class);
        if (form == null) {
            FormInfo.FORM_INFOS = new HashMap<>();
            CONFIG.set("FormInfo",FormInfo.FORM_INFOS);
            return;
        }
        FormInfo.FORM_INFOS = form;
    }
    public static void loadRestaurants() {
        List<Restaurant> rest = CONFIG.getList("restaurants", Restaurant.class);
        if (rest == null) {
            Restaurant.RESTAURANTS = new ArrayList<>();
            CONFIG.set("restaurants",Restaurant.RESTAURANTS);
            return;
        }
        Restaurant.RESTAURANTS = rest;
    }
    public static void loadStudents() {
        List<Student> stu = CONFIG.getList("students",Student.class);
        if (stu == null) {
            Student.STUDENTS = new ArrayList<>();
            CONFIG.set("students",Student.STUDENTS);
            return;
        }
        Student.STUDENTS = stu;
    }
    public static void end() {
        CONFIG.saveData();
    }
}
