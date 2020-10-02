/*
 * Created by JFormDesigner on Thu Oct 01 09:36:04 CST 2020
 */

package com.cutesmouse.s206Order.window;

import javax.swing.event.*;

import com.cutesmouse.s206Order.form.FormInfo;
import com.cutesmouse.s206Order.restaurant.Meal;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.student.OrderedItem;
import com.cutesmouse.s206Order.time.TimeStamp;
import com.cutesmouse.s206Order.utils.DayOrderedManager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class ShowData extends JPanel {
    private FormInfo info;
    private final TimeStamp time;
    public ShowData(FormInfo info) {
        this(info.getTime());
        this.info = info;
    }
    public ShowData(TimeStamp time) {
        this.time = time;
        initComponents();
        ArrayList<OrderedItem> orders = DayOrderedManager.getAllOrderedOn(time);
        order.setListData(orders.toArray());
        setAmountText(orders);
        total.setText("今日總價格: "+DayOrderedManager.getAllOrderedOn(time).stream().mapToInt(p -> p.Totalprice).sum());
    }
    public ShowData(TimeStamp[] time) {
        if (time.length == 0) {
            this.time = null;
            return;
        }
        this.time = time[0];
        initComponents();
        ArrayList<OrderedItem> orders = Arrays.stream(time).flatMap(p -> DayOrderedManager
                .getAllOrderedOn(p).stream()).collect(Collectors.toCollection(ArrayList::new));
        order.setListData(orders.toArray());
        setAmountText(orders);
        total.setText("今日總價格: "+orders.stream().mapToInt(p -> p.Totalprice).sum());
    }

    private void setAmountText(ArrayList<OrderedItem> orders) {
        HashMap<Restaurant,HashMap<Meal,Integer>> COUNTER = new HashMap<>();
        for (OrderedItem o : orders) {
            if (COUNTER.containsKey(o.restaurant)) {
                HashMap<Meal, Integer> mealIntegerHashMap = COUNTER.get(o.restaurant);
                if (mealIntegerHashMap.containsKey(o.ordered.meal)) {
                    mealIntegerHashMap.put(o.ordered.meal, mealIntegerHashMap.get(o.ordered.meal) +o.ordered.amount);
                } else {
                    mealIntegerHashMap.put(o.ordered.meal,o.ordered.amount);
                }
            } else {
                HashMap<Meal,Integer> insert = new HashMap<>();
                insert.put(o.ordered.meal,o.ordered.amount);
                COUNTER.put(o.restaurant,insert);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Restaurant r : COUNTER.keySet()) {
            sb.append(r.name).append(" (TEL: ").append(r.tel).append(")").append("\n");
            HashMap<Meal, Integer> mealIntegerHashMap = COUNTER.get(r);
            for (Meal meal : mealIntegerHashMap.keySet()) {
                sb.append("\t").append(meal.name).append(": ").append(mealIntegerHashMap.get(meal)).append("\n");
            }
        }
        amount.setText(sb.toString());
        scrollPane2.getVerticalScrollBar().setValue(0);
    }

    private void removeMeals(ActionEvent e) {
        if (order.getSelectedValue() == null) return;
        OrderedItem order = (OrderedItem) this.order.getSelectedValue();
        order.student.ORDERED.remove(order);
        ArrayList<OrderedItem> orders = DayOrderedManager.getAllOrderedOn(time);
        this.order.setListData(orders.toArray());
        total.setText("今日總價格: "+orders.stream().mapToInt(p -> p.Totalprice).sum());
        setAmountText(orders);
        remove.setEnabled(false);
    }

    private void mealsValueChanged(ListSelectionEvent e) {
        if (time.hasPast()) {
            remove.setEnabled(false);
            return;
        }
        remove.setEnabled(order.getSelectedValue() != null);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        order = new JList();
        remove = new JButton();
        total = new JTextField();
        scrollPane2 = new JScrollPane();
        amount = new JTextArea();

        //======== this ========
        setLayout(null);

        //======== scrollPane1 ========
        {

            //---- order ----
            order.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            order.addListSelectionListener(e -> mealsValueChanged(e));
            scrollPane1.setViewportView(order);
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

        //---- total ----
        total.setText("\u4eca\u65e5\u7e3d\u50f9\u683c");
        total.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
        total.setEditable(false);
        total.setFocusable(false);
        total.setBorder(null);
        add(total);
        total.setBounds(5, 160, 550, 40);

        //======== scrollPane2 ========
        {
            scrollPane2.setBorder(null);

            //---- amount ----
            amount.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            amount.setEditable(false);
            amount.setFocusable(false);
            amount.setBorder(null);
            amount.setOpaque(false);
            scrollPane2.setViewportView(amount);
        }
        add(scrollPane2);
        scrollPane2.setBounds(5, 200, 550, 140);

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
    private JList order;
    private JButton remove;
    private JTextField total;
    private JScrollPane scrollPane2;
    private JTextArea amount;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
