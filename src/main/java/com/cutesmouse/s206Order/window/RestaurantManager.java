/*
 * Created by JFormDesigner on Sun Sep 27 14:55:30 CST 2020
 */

package com.cutesmouse.s206Order.window;

import javax.swing.event.*;
import com.cutesmouse.s206Order.Main;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.restaurant.RestaurantBuilder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class RestaurantManager extends JPanel {
    public RestaurantManager() {
        initComponents();
        restaurants.addListSelectionListener(this::valueChanged);
        refresh(null);
    }

    private void refresh(ActionEvent e) {
        remove.setEnabled(false);
        edit.setEnabled(false);
        restaurants.setListData(Restaurant.RESTAURANTS.toArray());
    }

    private void add(ActionEvent e) {
        new CreateResturant(new RestaurantBuilder()).setVisible(true);
    }

    private void remove(ActionEvent e) {
        Object o = restaurants.getSelectedValue();
        if (o == null) {
            remove.setEnabled(false);
            edit.setEnabled(false);
            return;
        }
        Restaurant.RESTAURANTS.remove(o);
        refresh(e);
    }

    private void edit(ActionEvent e) {
        Object o = restaurants.getSelectedValue();
        if (o == null) {
            remove.setEnabled(false);
            edit.setEnabled(false);
            return;
        }
        new CreateResturant(((Restaurant) o)).setVisible(true);
    }

    private void valueChanged(ListSelectionEvent e) {
        Object o = restaurants.getSelectedValue();
        if (o == null) {
            remove.setEnabled(false);
            edit.setEnabled(false);
        } else {
            remove.setEnabled(true);
            edit.setEnabled(true);
        }
    }

    private void sort(ActionEvent e) {
        ArrayList<Restaurant> c = new ArrayList<>(Restaurant.RESTAURANTS);
        Collections.sort(c, Comparator.comparing(p -> p.name));
        restaurants.setListData(c.toArray());
        remove.setEnabled(false);
        edit.setEnabled(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        restaurants = new JList();
        edit = new JButton();
        add = new JButton();
        remove = new JButton();
        refresh = new JButton();
        refresh2 = new JButton();

        //======== this ========
        setLayout(null);

        //======== scrollPane1 ========
        {

            //---- restaurants ----
            restaurants.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            scrollPane1.setViewportView(restaurants);
        }
        add(scrollPane1);
        scrollPane1.setBounds(20, 20, 670, 365);

        //---- edit ----
        edit.setForeground(Color.red);
        edit.setIcon(new ImageIcon(getClass().getResource("/edit.png")));
        edit.setBorderPainted(false);
        edit.setContentAreaFilled(false);
        edit.setEnabled(false);
        edit.addActionListener(e -> edit(e));
        add(edit);
        edit.setBounds(700, 92, 30, 30);

        //---- add ----
        add.setForeground(new Color(0, 153, 0));
        add.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        add.setBorder(null);
        add.setOpaque(false);
        add.setBorderPainted(false);
        add.setContentAreaFilled(false);
        add.setFocusPainted(false);
        add.addActionListener(e -> add(e));
        add(add);
        add.setBounds(700, 20, 30, 30);

        //---- remove ----
        remove.setForeground(Color.red);
        remove.setIcon(new ImageIcon(getClass().getResource("/minus.png")));
        remove.setBorderPainted(false);
        remove.setContentAreaFilled(false);
        remove.setEnabled(false);
        remove.setFocusPainted(false);
        remove.addActionListener(e -> remove(e));
        add(remove);
        remove.setBounds(700, 56, 30, 30);

        //---- refresh ----
        refresh.setForeground(Color.red);
        refresh.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
        refresh.setBorderPainted(false);
        refresh.setContentAreaFilled(false);
        refresh.setFocusPainted(false);
        refresh.addActionListener(e -> refresh(e));
        add(refresh);
        refresh.setBounds(700, 128, 30, 30);

        //---- refresh2 ----
        refresh2.setForeground(Color.red);
        refresh2.setIcon(new ImageIcon(getClass().getResource("/sort.png")));
        refresh2.setBorderPainted(false);
        refresh2.setContentAreaFilled(false);
        refresh2.setFocusPainted(false);
        refresh2.addActionListener(e -> sort(e));
        add(refresh2);
        refresh2.setBounds(700, 355, 30, 30);

        setPreferredSize(new Dimension(740, 410));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JList restaurants;
    private JButton edit;
    private JButton add;
    private JButton remove;
    private JButton refresh;
    private JButton refresh2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
