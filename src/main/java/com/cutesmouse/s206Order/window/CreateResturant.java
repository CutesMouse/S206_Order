/*
 * Created by JFormDesigner on Sun Sep 27 09:03:26 CST 2020
 */

package com.cutesmouse.s206Order.window;

import javax.swing.event.*;
import com.cutesmouse.s206Order.restaurant.Meal;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.restaurant.RestaurantBuilder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class CreateResturant extends JFrame {
    private final RestaurantBuilder BUILDER;
    private final Restaurant EDITING;
    private final boolean EDIT;
    public CreateResturant(Restaurant edit) {
        EDITING = edit;
        EDIT = true;
        BUILDER = null;
        initComponents();
        meals.addListSelectionListener(this::valueChanged);
        name.setText(EDITING.name);
        tel.setText(EDITING.tel);
        meals.setListData(EDITING.meals.toArray());
    }
    public CreateResturant(RestaurantBuilder builder) {
        BUILDER = builder;
        EDITING = null;
        EDIT = false;
        initComponents();
        meals.addListSelectionListener(this::valueChanged);
    }
    private void add(ActionEvent e) {
        AddMealPanel data = new AddMealPanel();
        int result = JOptionPane.showConfirmDialog(this, data,"新增餐點",JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,null);
        if (result == JOptionPane.OK_OPTION) {
            if (!data.getMealPrice().matches("\\d+")) {
                JOptionPane.showMessageDialog(this,"您輸入的價錢不是正整數!","錯誤",JOptionPane.WARNING_MESSAGE);
                return;
            }
            String name = data.getMealName();
            int price = Integer.parseInt(data.getMealPrice());
            if (EDIT) EDITING.meals.add(new Meal(name,price));
            else BUILDER.withMeal(new Meal(name,price));
            refresh(null);
        }
    }

    private void destroy(ActionEvent e) {
        this.dispose();
    }

    private void refresh(ActionEvent e) {
        if (EDIT) meals.setListData(EDITING.meals.toArray());
        else meals.setListData(BUILDER.meals.toArray());
        remove.setEnabled(false);
    }

    private void removeMeals(ActionEvent e) {
        if (meals.getSelectedValue() == null) {
            remove.setEnabled(false);
            return;
        }
        if (EDIT) EDITING.meals.remove(meals.getSelectedValue());
        else BUILDER.meals.remove(meals.getSelectedValue());
        refresh(null);
    }

    private void valueChanged(ListSelectionEvent e) {
        if (meals.getSelectedValue() != null) remove.setEnabled(true);
    }

    private void build(ActionEvent e) {
        if (name.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"建立失敗! 沒有輸入餐廳名稱","錯誤",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (EDIT) {
            EDITING.name = name.getText();
            EDITING.tel = tel.getText();
        } else BUILDER.withName(name.getText())
                .withTel(tel.getText())
                .build();
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        textField1 = new JTextField();
        textField2 = new JTextField();
        name = new JTextField();
        tel = new JTextField();
        scrollPane1 = new JScrollPane();
        meals = new JList();
        button1 = new JButton();
        button2 = new JButton();
        add = new JButton();
        remove = new JButton();
        refresh = new JButton();

        //======== this ========
        setTitle("\u5efa\u7acb\u9910\u5ef3");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- textField1 ----
        textField1.setText("*\u9910\u5ef3\u540d\u7a31");
        textField1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
        textField1.setEditable(false);
        textField1.setBorder(null);
        contentPane.add(textField1);
        textField1.setBounds(30, 25, 90, 33);

        //---- textField2 ----
        textField2.setText("\u9910\u5ef3\u96fb\u8a71");
        textField2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
        textField2.setEditable(false);
        textField2.setBorder(null);
        contentPane.add(textField2);
        textField2.setBounds(35, 80, 85, 33);

        //---- name ----
        name.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        contentPane.add(name);
        name.setBounds(130, 25, 490, name.getPreferredSize().height);

        //---- tel ----
        tel.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        contentPane.add(tel);
        tel.setBounds(130, 80, 490, 33);

        //======== scrollPane1 ========
        {

            //---- meals ----
            meals.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            scrollPane1.setViewportView(meals);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(35, 140, 550, 155);

        //---- button1 ----
        button1.setText("\u78ba\u8a8d");
        button1.addActionListener(e -> build(e));
        contentPane.add(button1);
        button1.setBounds(485, 305, 70, 30);

        //---- button2 ----
        button2.setText("\u53d6\u6d88");
        button2.addActionListener(e -> destroy(e));
        contentPane.add(button2);
        button2.setBounds(560, 305, 70, 30);

        //---- add ----
        add.setForeground(new Color(0, 153, 0));
        add.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        add.setBorder(null);
        add.setOpaque(false);
        add.setBorderPainted(false);
        add.setContentAreaFilled(false);
        add.setFocusPainted(false);
        add.addActionListener(e -> add(e));
        contentPane.add(add);
        add.setBounds(590, 140, 30, 30);

        //---- remove ----
        remove.setForeground(Color.red);
        remove.setIcon(new ImageIcon(getClass().getResource("/minus.png")));
        remove.setBorderPainted(false);
        remove.setContentAreaFilled(false);
        remove.setEnabled(false);
        remove.setFocusPainted(false);
        remove.addActionListener(e -> removeMeals(e));
        contentPane.add(remove);
        remove.setBounds(590, 175, 30, 30);

        //---- refresh ----
        refresh.setForeground(Color.red);
        refresh.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
        refresh.setBorderPainted(false);
        refresh.setContentAreaFilled(false);
        refresh.setFocusPainted(false);
        refresh.addActionListener(e -> refresh(e));
        contentPane.add(refresh);
        refresh.setBounds(590, 210, 30, 30);

        contentPane.setPreferredSize(new Dimension(675, 360));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField textField1;
    private JTextField textField2;
    private JTextField name;
    private JTextField tel;
    private JScrollPane scrollPane1;
    private JList meals;
    private JButton button1;
    private JButton button2;
    private JButton add;
    private JButton remove;
    private JButton refresh;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
