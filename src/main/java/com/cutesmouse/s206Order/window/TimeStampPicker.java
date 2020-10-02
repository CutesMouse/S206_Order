/*
 * Created by JFormDesigner on Sun Sep 27 16:33:14 CST 2020
 */

package com.cutesmouse.s206Order.window;

import java.awt.event.*;
import com.cutesmouse.s206Order.time.TimeStamp;
import com.cutesmouse.s206Order.time.TimeStampPickerEvent;
import com.cutesmouse.s206Order.utils.DisplayText;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class TimeStampPicker extends JPanel {
    private TimeStampPickerEvent Listener;
    public TimeStamp getTimeStamp() {
        if (years.getSelectedItem() == null || months.getSelectedItem() == null || weekOfMonth.getSelectedItem() == null) return null;
        if (weekOfMonth.getSelectedItem().toString().startsWith("第") && dayOfWeek.getSelectedItem() == null) return null;
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        c.setTimeInMillis(0L);
        c.set(Calendar.YEAR,Int(years.getSelectedItem().toString()));
        c.set(Calendar.MONTH,Int(months.getSelectedItem().toString())-1);
        if (weekOfMonth.getSelectedItem().toString().startsWith("第")) {
            c.set(Calendar.WEEK_OF_MONTH,Int(weekOfMonth.getSelectedItem().toString().replaceAll("第(\\d+)週","$1")));
            c.set(Calendar.DAY_OF_WEEK,dayOfWeek.getSelectedItem().hashCode());
        } else {
            c.set(Calendar.DAY_OF_MONTH,Int(weekOfMonth.getSelectedItem().toString().replaceAll("(\\d+)日","$1")));
        }
        return new TimeStamp(c);
    }
    public void addSubmitListener(TimeStampPickerEvent e) {
        this.Listener = e;
    }
    private int Int(String s) {
        return Integer.parseInt(s);
    }
    public TimeStampPicker() {
        initComponents();
        years.addItem("2020");
        years.addItem("2021");
        years.addItem("2022");
        for (int i = 1; i <= 12; i++) months.addItem(i+"");
    }
    private void reload(ItemEvent e) {
        weekOfMonth.removeAllItems();
        dayOfWeek.removeAllItems();
        if (years.getSelectedItem() == null || months.getSelectedItem() == null) return;
        int year = Integer.parseInt(years.getSelectedItem().toString());
        int month = Integer.parseInt(months.getSelectedItem().toString());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month-1);
        for (int i = 1; i <= cal.getActualMaximum(Calendar.WEEK_OF_MONTH); i++) {
            weekOfMonth.addItem("第"+i+"週");
        }
        for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            weekOfMonth.addItem(i+"日");
        }
    }

    private void LoadDay(ItemEvent e) {
        dayOfWeek.removeAllItems();
        if (weekOfMonth.getSelectedItem() == null) return;
        if (!weekOfMonth.getSelectedItem().toString().startsWith("第")) {
            dayOfWeek.setEnabled(false);
        } else {
            int year = Integer.parseInt(years.getSelectedItem().toString());
            int month = Integer.parseInt(months.getSelectedItem().toString());
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0L);
            cal.set(Calendar.YEAR,year);
            cal.set(Calendar.MONTH,month-1);
            int week = Integer.parseInt(weekOfMonth.getSelectedItem().toString().replaceAll("第(\\d+)週", "$1"));
            cal.set(Calendar.WEEK_OF_MONTH, week);
            dayOfWeek.setEnabled(true);
            int from = 1;
            int end = 7;
            if (week == 1) {
                cal.set(Calendar.DAY_OF_MONTH,1);
                from = cal.get(Calendar.DAY_OF_WEEK);
                end = 7;
            }
            if (week == cal.getActualMaximum(Calendar.WEEK_OF_MONTH)) {
                from = 1;
                cal.set(Calendar.MONTH,month);
                cal.set(Calendar.DAY_OF_MONTH,0);
                end = cal.get(Calendar.DAY_OF_WEEK);
            }
            for (int i = from; i <= end; i++) {
                final int c = i;
                dayOfWeek.addItem(new Object() {
                    @Override
                    public String toString() {
                        return DisplayText.DAYOFWEEK(c);
                    }

                    @Override
                    public int hashCode() {
                        return c;
                    }
                });
            }
        }
    }

    private void setToday(ActionEvent e) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        years.setSelectedItem(cal.get(Calendar.YEAR)+"");
        months.setSelectedItem(""+Integer.parseInt(new SimpleDateFormat("MM").format(new Date(cal.getTimeInMillis()))));
        weekOfMonth.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH)+"日");
    }

    private void submit(ActionEvent e) {
        if (this.Listener == null) return;
        this.Listener.run(false,this);
    }
    public TimeStamp[] getTimeStamps() {
        return SelectedStamps.toArray(new TimeStamp[0]);
    }

    private ArrayList<TimeStamp> SelectedStamps;
    private TimeStamp p1;
    private void MultiSelection(ActionEvent e) {
        if (p1 == null) {
            this.p1 = getTimeStamp();
            range.setIcon(new ImageIcon(getClass().getResource("/point2.png")));
            return;
        }
        TimeStamp p2 = getTimeStamp();
        if (p1.toDate().getTime() > p2.toDate().getTime()) {
            p2 = this.p1;
            p1 = getTimeStamp();
        }
        SelectedStamps = new ArrayList<>();
        for (long a = p1.toDate().getTime(); a <= p2.toDate().getTime(); a+= 1000 * 60 * 60 *24) {
            SelectedStamps.add(new TimeStamp(a));
        }
        range.setIcon(new ImageIcon(getClass().getResource("/point1.png")));
        if (this.Listener == null) return;
        this.Listener.run(true,this);
        p1 = null;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel3 = new JPanel();
        panel1 = new JPanel();
        years = new JComboBox();
        date = new JTextField();
        panel2 = new JPanel();
        months = new JComboBox();
        date2 = new JTextField();
        weekOfMonth = new JComboBox();
        dayOfWeek = new JComboBox();
        button1 = new JButton();
        range = new JButton();
        button2 = new JButton();

        //======== this ========
        setLayout(null);

        //======== panel3 ========
        {
            panel3.setLayout(null);

            //======== panel1 ========
            {
                panel1.setLayout(null);

                //---- years ----
                years.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
                years.addItemListener(e -> reload(e));
                panel1.add(years);
                years.setBounds(0, 0, 130, years.getPreferredSize().height);

                //---- date ----
                date.setForeground(Color.black);
                date.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 22));
                date.setEditable(false);
                date.setBorder(null);
                date.setFocusable(false);
                date.setRequestFocusEnabled(false);
                date.setText("\u5e74");
                panel1.add(date);
                date.setBounds(135, 0, 35, 33);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel1.getComponentCount(); i++) {
                        Rectangle bounds = panel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel1.setMinimumSize(preferredSize);
                    panel1.setPreferredSize(preferredSize);
                }
            }
            panel3.add(panel1);
            panel1.setBounds(new Rectangle(new Point(0, 0), panel1.getPreferredSize()));

            //======== panel2 ========
            {
                panel2.setLayout(null);

                //---- months ----
                months.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
                months.addItemListener(e -> reload(e));
                panel2.add(months);
                months.setBounds(0, 0, 130, months.getPreferredSize().height);

                //---- date2 ----
                date2.setForeground(Color.black);
                date2.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 22));
                date2.setEditable(false);
                date2.setBorder(null);
                date2.setFocusable(false);
                date2.setRequestFocusEnabled(false);
                date2.setText("\u6708");
                panel2.add(date2);
                date2.setBounds(135, 0, 35, 33);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel2.getComponentCount(); i++) {
                        Rectangle bounds = panel2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel2.setMinimumSize(preferredSize);
                    panel2.setPreferredSize(preferredSize);
                }
            }
            panel3.add(panel2);
            panel2.setBounds(170, 0, 165, 33);

            //---- weekOfMonth ----
            weekOfMonth.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            weekOfMonth.addItemListener(e -> LoadDay(e));
            panel3.add(weekOfMonth);
            weekOfMonth.setBounds(345, 0, 130, 33);

            //---- dayOfWeek ----
            dayOfWeek.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel3.add(dayOfWeek);
            dayOfWeek.setBounds(485, 0, 130, 33);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel3.getComponentCount(); i++) {
                    Rectangle bounds = panel3.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel3.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel3.setMinimumSize(preferredSize);
                panel3.setPreferredSize(preferredSize);
            }
        }
        add(panel3);
        panel3.setBounds(new Rectangle(new Point(35, 0), panel3.getPreferredSize()));

        //---- button1 ----
        button1.setOpaque(false);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        button1.setIcon(new ImageIcon(getClass().getResource("/today.png")));
        button1.setToolTipText("\u8a2d\u5b9a\u70ba\u4eca\u5929");
        button1.addActionListener(e -> setToday(e));
        add(button1);
        button1.setBounds(0, 0, 30, 30);

        //---- range ----
        range.setOpaque(false);
        range.setContentAreaFilled(false);
        range.setBorderPainted(false);
        range.setFocusPainted(false);
        range.setIcon(new ImageIcon(getClass().getResource("/point1.png")));
        range.setToolTipText("\u8a2d\u5b9a\u7bc4\u570d\uff0c\u9ede\u9078\u4e4b\u5f8c\u6703\u8a2d\u5b9a\u70ba\u8d77\u59cb\u9ede\uff0c\n\u4e4b\u5f8c\u8acb\u518d\u9078\u53d6\u53e6\u4e00\u500b\u9ede\uff0c\u518d\u9ede\u64ca\u6b64\u6309\u9215\u4e00\u6b21\u5373\u53ef\u3002");
        range.setSelectedIcon(null);
        range.addActionListener(e -> MultiSelection(e));
        add(range);
        range.setBounds(655, 0, 27, 30);

        //---- button2 ----
        button2.setOpaque(false);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);
        button2.setIcon(new ImageIcon(getClass().getResource("/send.png")));
        button2.setMnemonic('\n');
        button2.setToolTipText("\u9001\u51fa");
        button2.addActionListener(e -> submit(e));
        add(button2);
        button2.setBounds(690, 0, 30, 30);

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
    private JPanel panel3;
    private JPanel panel1;
    private JComboBox years;
    private JTextField date;
    private JPanel panel2;
    private JComboBox months;
    private JTextField date2;
    private JComboBox weekOfMonth;
    private JComboBox dayOfWeek;
    private JButton button1;
    private JButton range;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
