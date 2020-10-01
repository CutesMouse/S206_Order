/*
 * Created by JFormDesigner on Thu Oct 01 08:57:12 CST 2020
 */

package com.cutesmouse.s206Order.formWindow;

import com.cutesmouse.s206Order.form.MealData;
import com.cutesmouse.s206Order.utils.DisplayText;

import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class SingleSubmit extends JPanel {
    public SingleSubmit(MealData md) {
        initComponents();
        weekDays.setIcon(new ImageIcon(getClass().getResource("/w_"+md.getTime().day+".png")));
        item.setText(md.getRestaurant().name+"/"+md.getMeal().name+"/"+md.getMeal().price+" x"+md.getAmount());
        price.setText(md.getPrice()+"");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        weekDays = new JLabel();
        item = new JTextField();
        price = new JTextField();

        //======== this ========
        setPreferredSize(new Dimension(590, 300));
        setLayout(null);

        //---- weekDays ----
        weekDays.setIcon(new ImageIcon(getClass().getResource("/w_1.png")));
        add(weekDays);
        weekDays.setBounds(10, 5, 30, 30);

        //---- item ----
        item.setText("\u9ede\u9910\u5167\u5bb9");
        item.setEditable(false);
        item.setBorder(null);
        item.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(item);
        item.setBounds(45, 5, 485, 30);

        //---- price ----
        price.setText("100");
        price.setEditable(false);
        price.setBorder(null);
        price.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(price);
        price.setBounds(535, 5, 55, 30);

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
    private JLabel weekDays;
    private JTextField item;
    private JTextField price;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
