/*
 * Created by JFormDesigner on Wed Sep 30 20:20:19 CST 2020
 */

package com.cutesmouse.s206Order.formWindow;

import com.cutesmouse.s206Order.form.FormInfo;
import com.cutesmouse.s206Order.form.MealData;
import com.cutesmouse.s206Order.restaurant.Meal;

import java.awt.*;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class MealPanel extends JPanel {
    private final Meal meal;
    private final FormInfo info;
    public MealPanel(Meal m, FormInfo info) {
        this.info = info;
        this.meal = m;
        initComponents();
        this.content.setText(m.name+" / " + m.price+"$");
    }

    public FormInfo getInfo() {
        return info;
    }

    public Meal getMeal() {
        return meal;
    }
    public MealData getMealData() {
        return new MealData(meal,getAmount(),info.getTime());
    }
    public int getAmount() {
        if (!this.amount.getText().matches("\\d+")) {
            return -1;
        }
        return Integer.parseInt(this.amount.getText());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        content = new JTextField();
        amount = new JTextField();

        //======== this ========
        setLayout(null);

        //---- content ----
        content.setText("\u8a02\u9910\u5167\u5bb9");
        content.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        content.setEditable(false);
        content.setBorder(null);
        add(content);
        content.setBounds(5, 5, 680, content.getPreferredSize().height);

        //---- amount ----
        amount.setText("0");
        amount.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(amount);
        amount.setBounds(695, 5, 40, 27);

        setPreferredSize(new Dimension(740, 30));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField content;
    private JTextField amount;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
