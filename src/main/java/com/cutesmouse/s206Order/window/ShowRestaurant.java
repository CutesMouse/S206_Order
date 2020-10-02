/*
 * Created by JFormDesigner on Thu Oct 01 09:36:04 CST 2020
 */

package com.cutesmouse.s206Order.window;

import javax.swing.event.*;

import com.cutesmouse.s206Order.form.FormInfo;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.time.TimeStamp;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class ShowRestaurant extends JPanel {
    private final FormInfo info;
    public ShowRestaurant(FormInfo info) {
        this.info = info;
        initComponents();
        meals.setListData(info.getRestaurants().toArray());
    }

    private void removeMeals(ActionEvent e) {
        if (meals.getSelectedValue() == null) return;
        info.getRestaurants().remove(meals.getSelectedValue());
        if (info.getRestaurants().size() == 0 && info.isToggled()) {
            info.setToggled(false);
        }
        meals.setListData(info.getRestaurants().toArray());
        remove.setEnabled(false);
    }

    private void mealsValueChanged(ListSelectionEvent e) {
        if (info.getTime().hasPast()) {
            remove.setEnabled(false);
            return;
        }
        remove.setEnabled(meals.getSelectedValue() != null);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        meals = new JList();
        remove = new JButton();

        //======== this ========
        setLayout(null);

        //======== scrollPane1 ========
        {

            //---- meals ----
            meals.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            meals.addListSelectionListener(e -> mealsValueChanged(e));
            scrollPane1.setViewportView(meals);
        }
        add(scrollPane1);
        scrollPane1.setBounds(5, 0, 550, 155);

        //---- remove ----
        remove.setForeground(Color.red);
        remove.setIcon(new ImageIcon(getClass().getResource("/minus.png")));
        remove.setBorderPainted(false);
        remove.setContentAreaFilled(false);
        remove.setEnabled(false);
        remove.setFocusPainted(false);
        remove.addActionListener(e -> removeMeals(e));
        add(remove);
        remove.setBounds(560, 0, 30, 30);

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
    private JScrollPane scrollPane1;
    private JList meals;
    private JButton remove;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
