/*
 * Created by JFormDesigner on Wed Sep 30 20:20:09 CST 2020
 */

package com.cutesmouse.s206Order.formWindow;

import javax.swing.border.*;

import com.cutesmouse.s206Order.form.MealData;
import com.cutesmouse.s206Order.restaurant.Meal;
import com.cutesmouse.s206Order.restaurant.Restaurant;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class RestaurantPanel extends JPanel {
    private final Restaurant restaurant;
    public RestaurantPanel(Restaurant r) {
        this.restaurant = r;
        initComponents();
        this.name.setText(r.name);
        for (Meal m : r.meals) {
            this.meals.add(new MealPanel(m));
        }
    }

    public ArrayList<MealData> getMealDatas() {
        ArrayList<MealData> mds = new ArrayList<>();
        for (Component c : meals.getComponents()) {
            if (!(c instanceof MealPanel)) continue;
            MealPanel mp = (MealPanel) c;
            if (mp.getAmount() > 0) {
                MealData data = mp.getMealData();
                data.setRestaurant(restaurant);
                mds.add(data);
            }
        }
        return mds;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JButton();
        name = new JTextField();
        scrollPane1 = new JScrollPane();
        meals = new JPanel();

        //======== this ========
        setLayout(null);

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/RestaurantInfo.png")));
        label1.setBorderPainted(false);
        label1.setFocusPainted(false);
        label1.setContentAreaFilled(false);
        add(label1);
        label1.setBounds(25, 5, 90, 30);

        //---- name ----
        name.setText("\u9910\u5ef3\u540d\u7a31");
        name.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 22));
        name.setEditable(false);
        name.setBorder(null);
        add(name);
        name.setBounds(45, 40, 435, 35);

        //======== scrollPane1 ========
        {
            scrollPane1.setBorder(new EmptyBorder(5, 5, 5, 5));

            //======== meals ========
            {
                meals.setBorder(null);
                meals.setLayout(new BoxLayout(meals, BoxLayout.Y_AXIS));
            }
            scrollPane1.setViewportView(meals);
        }
        add(scrollPane1);
        scrollPane1.setBounds(70, 80, 760, 350);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton label1;
    private JTextField name;
    private JScrollPane scrollPane1;
    private JPanel meals;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
