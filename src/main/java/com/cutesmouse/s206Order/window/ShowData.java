/*
 * Created by JFormDesigner on Thu Oct 01 09:36:04 CST 2020
 */

package com.cutesmouse.s206Order.window;

import javax.swing.event.*;

import com.cutesmouse.s206Order.form.FormInfo;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.student.OrderedItem;
import com.cutesmouse.s206Order.time.TimeStamp;
import com.cutesmouse.s206Order.utils.DayOrderedManager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
        order.setListData(DayOrderedManager.getAllOrderedOn(time).toArray());
        total.setText("今日總價格: "+DayOrderedManager.getAllOrderedOn(time).stream().mapToInt(p -> p.Totalprice).sum());
    }

    private void removeMeals(ActionEvent e) {
        if (order.getSelectedValue() == null) return;
        OrderedItem order = (OrderedItem) this.order.getSelectedValue();
        order.student.ORDERED.remove(order);
        this.order.setListData(DayOrderedManager.getAllOrderedOn(time).toArray());
        total.setText("今日總價格: "+DayOrderedManager.getAllOrderedOn(time).stream().mapToInt(p -> p.Totalprice).sum());
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
