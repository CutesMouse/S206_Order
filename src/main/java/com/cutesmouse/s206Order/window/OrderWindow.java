/*
 * Created by JFormDesigner on Sun Sep 27 16:16:47 CST 2020
 */

package com.cutesmouse.s206Order.window;

import java.awt.event.*;

import com.cutesmouse.s206Order.Main;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.restaurant.RestaurantBuilder;
import com.cutesmouse.s206Order.time.TimeStamp;
import com.cutesmouse.s206Order.utils.DisplayText;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

/**
 * @author CutesMouse
 */
public class OrderWindow extends JFrame {
    private int weekOffset;
    public OrderWindow() {
        initComponents();
        OrderPicker.addSubmitListener(this::orderPicker);
        weekOffset = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.WEEK_OF_MONTH);
        loadNest();
        restaurants.addListSelectionListener(this::valueChanged);
        refresh(null);
    }
    private void loadNest() {
        daysNest.removeAll();
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        int week = weekOffset;
        cal.set(Calendar.WEEK_OF_MONTH, week);
        week = cal.get(Calendar.WEEK_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);


        int from = 1;
        int end = 7;
        if (week == 1) {
            System.out.println(cal.get(Calendar.DAY_OF_MONTH));
            from = cal.get(Calendar.DAY_OF_WEEK);
            end = 7;
        }
        if (week == cal.getActualMaximum(Calendar.WEEK_OF_MONTH)) {
            from = 1;
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
            cal.set(Calendar.DAY_OF_MONTH, 0);
            end = cal.get(Calendar.DAY_OF_WEEK);
        }
        if (from > 1) {
            for (int i = 1; i < from; i++) {
                daysNest.add(new DayBlock(new TimeStamp(year, month, week, i), true));
            }
        }
        for (int i = from; i <= end; i++) {
            daysNest.add(new DayBlock(new TimeStamp(year, month, week, i)));
        }
        if (end != 7) {
            for (int i = end + 1; i <= 7; i++) {
                daysNest.add(new DayBlock(new TimeStamp(year, month, week, i), true));
            }
        }
        daysNest.updateUI();
    }
    public void orderPicker(ActionEvent e) {
        System.out.println(OrderPicker.getTimeStamp());
    }

    private void next_week(ActionEvent e) {
        weekOffset++;
        loadNest();
    }

    private void last_week(ActionEvent e) {
        weekOffset--;
        loadNest();
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

    private void setToday(ActionEvent e) {
        weekOffset = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.WEEK_OF_MONTH);
        loadNest();
    }

    private void thisWindowClosing(WindowEvent e) {
        Main.end();
        dispose();
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        Order = new JScrollPane();
        panel1 = new JPanel();
        OrderPicker = new TimeStampPicker();
        querySingle = new JScrollPane();
        queryAll = new JScrollPane();
        panel4 = new JPanel();
        timeStampPicker1 = new TimeStampPicker();
        settings = new JScrollPane();
        panel2 = new JPanel();
        textField1 = new JTextField();
        daysNest = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        this2 = new JPanel();
        scrollPane1 = new JScrollPane();
        restaurants = new JList();
        edit = new JButton();
        add = new JButton();
        remove = new JButton();
        refresh = new JButton();
        refresh2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setTitle("\u9ede\u9910\u4ecb\u9762");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 18));

            //======== Order ========
            {

                //======== panel1 ========
                {
                    panel1.setLayout(new FlowLayout());
                    panel1.add(OrderPicker);
                }
                Order.setViewportView(panel1);
            }
            tabbedPane1.addTab("\u9ede\u9910", Order);
            tabbedPane1.addTab("\u500b\u4eba\u67e5\u8a62", querySingle);

            //======== queryAll ========
            {

                //======== panel4 ========
                {
                    panel4.setLayout(new FlowLayout());
                    panel4.add(timeStampPicker1);
                }
                queryAll.setViewportView(panel4);
            }
            tabbedPane1.addTab("\u5168\u73ed\u67e5\u8a62", queryAll);

            //======== settings ========
            {

                //======== panel2 ========
                {
                    panel2.setLayout(null);

                    //---- textField1 ----
                    textField1.setText("\u8868\u55ae\u5167\u5bb9");
                    textField1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                    textField1.setEditable(false);
                    textField1.setFocusable(false);
                    textField1.setBorder(null);
                    panel2.add(textField1);
                    textField1.setBounds(25, 30, 105, 40);

                    //======== daysNest ========
                    {
                        daysNest.setPreferredSize(new Dimension(10, 210));
                        daysNest.setLayout(new FlowLayout());
                    }
                    panel2.add(daysNest);
                    daysNest.setBounds(25, 70, 1135, 235);

                    //---- button1 ----
                    button1.setIcon(new ImageIcon(getClass().getResource("/left_next.png")));
                    button1.setBorder(null);
                    button1.setOpaque(false);
                    button1.setBorderPainted(false);
                    button1.setFocusPainted(false);
                    button1.setContentAreaFilled(false);
                    button1.addActionListener(e -> next_week(e));
                    panel2.add(button1);
                    button1.setBounds(172, 35, 30, 30);

                    //---- button2 ----
                    button2.setIcon(new ImageIcon(getClass().getResource("/last_page.png")));
                    button2.setBorder(null);
                    button2.setOpaque(false);
                    button2.setBorderPainted(false);
                    button2.setFocusPainted(false);
                    button2.setContentAreaFilled(false);
                    button2.addActionListener(e -> last_week(e));
                    panel2.add(button2);
                    button2.setBounds(135, 35, 30, 30);

                    //======== this2 ========
                    {
                        this2.setLayout(null);

                        //======== scrollPane1 ========
                        {

                            //---- restaurants ----
                            restaurants.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
                            scrollPane1.setViewportView(restaurants);
                        }
                        this2.add(scrollPane1);
                        scrollPane1.setBounds(0, 20, 1080, 255);

                        //---- edit ----
                        edit.setForeground(Color.red);
                        edit.setIcon(new ImageIcon(getClass().getResource("/edit.png")));
                        edit.setBorderPainted(false);
                        edit.setContentAreaFilled(false);
                        edit.setEnabled(false);
                        edit.addActionListener(e -> edit(e));
                        this2.add(edit);
                        edit.setBounds(1090, 95, 30, 30);

                        //---- add ----
                        add.setForeground(new Color(0, 153, 0));
                        add.setIcon(new ImageIcon(getClass().getResource("/add.png")));
                        add.setBorder(null);
                        add.setOpaque(false);
                        add.setBorderPainted(false);
                        add.setContentAreaFilled(false);
                        add.setFocusPainted(false);
                        add.addActionListener(e -> add(e));
                        this2.add(add);
                        add.setBounds(1090, 25, 30, 30);

                        //---- remove ----
                        remove.setForeground(Color.red);
                        remove.setIcon(new ImageIcon(getClass().getResource("/minus.png")));
                        remove.setBorderPainted(false);
                        remove.setContentAreaFilled(false);
                        remove.setEnabled(false);
                        remove.setFocusPainted(false);
                        remove.addActionListener(e -> remove(e));
                        this2.add(remove);
                        remove.setBounds(1090, 60, 30, 30);

                        //---- refresh ----
                        refresh.setForeground(Color.red);
                        refresh.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
                        refresh.setBorderPainted(false);
                        refresh.setContentAreaFilled(false);
                        refresh.setFocusPainted(false);
                        refresh.addActionListener(e -> refresh(e));
                        this2.add(refresh);
                        refresh.setBounds(1090, 130, 30, 30);

                        //---- refresh2 ----
                        refresh2.setForeground(Color.red);
                        refresh2.setIcon(new ImageIcon(getClass().getResource("/sort.png")));
                        refresh2.setBorderPainted(false);
                        refresh2.setContentAreaFilled(false);
                        refresh2.setFocusPainted(false);
                        refresh2.addActionListener(e -> sort(e));
                        this2.add(refresh2);
                        refresh2.setBounds(1090, 240, 30, 30);
                    }
                    panel2.add(this2);
                    this2.setBounds(25, 330, 1135, 280);

                    //---- button3 ----
                    button3.setIcon(new ImageIcon(getClass().getResource("/today.png")));
                    button3.setBorder(null);
                    button3.setOpaque(false);
                    button3.setBorderPainted(false);
                    button3.setFocusPainted(false);
                    button3.setContentAreaFilled(false);
                    button3.addActionListener(e -> setToday(e));
                    panel2.add(button3);
                    button3.setBounds(209, 35, 30, 30);
                }
                settings.setViewportView(panel2);
            }
            tabbedPane1.addTab("\u83dc\u55ae\u8a2d\u5b9a", settings);
        }
        contentPane.add(tabbedPane1);
        tabbedPane1.setBounds(0, 0, 1170, 690);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JScrollPane Order;
    private JPanel panel1;
    private TimeStampPicker OrderPicker;
    private JScrollPane querySingle;
    private JScrollPane queryAll;
    private JPanel panel4;
    private TimeStampPicker timeStampPicker1;
    private JScrollPane settings;
    private JPanel panel2;
    private JTextField textField1;
    private JPanel daysNest;
    private JButton button1;
    private JButton button2;
    private JPanel this2;
    private JScrollPane scrollPane1;
    private JList restaurants;
    private JButton edit;
    private JButton add;
    private JButton remove;
    private JButton refresh;
    private JButton refresh2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
