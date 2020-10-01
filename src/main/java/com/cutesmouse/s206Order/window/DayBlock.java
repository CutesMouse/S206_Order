/*
 * Created by JFormDesigner on Sun Sep 27 21:37:19 CST 2020
 */

package com.cutesmouse.s206Order.window;

import com.cutesmouse.s206Order.form.FormInfo;
import com.cutesmouse.s206Order.time.TimeStamp;
import com.cutesmouse.s206Order.utils.DisplayText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author CutesMouse
 */
public class DayBlock extends JPanel {
    private ActionListener e;
    public static OrderWindow MainWINDOW;
    private final TimeStamp time;
    private FormInfo FORM;

    public TimeStamp getTimestamp() {
        return time;
    }

    public boolean hasData() {
        if (FORM == null) return false;
        return FORM.getResult().size() != 0;
    }

    public boolean hasRestaurant() {
        if (FORM == null) return false;
        return FORM.getRestaurants().size() != 0;
    }

    public boolean avaliableToFill() {
        if (FORM == null) return false;
        return FORM.isToggled();
    }

    public DayBlock(TimeStamp time, boolean enabled) {
        this(time);
        blank.setEnabled(enabled);
    }
    public DayBlock(TimeStamp time) {
        this.time = time;
        if (FormInfo.FORM_INFOS.containsKey(time)) FORM = FormInfo.FORM_INFOS.get(time);
        initComponents();
        dayOfWeek.setText(DisplayText.DAYOFWEEK(time.day));
        dayOfWeek.setForeground(new Color(0, 32, 137));
        // Init ICON
        if (time.toDate().getTime() < System.currentTimeMillis() && !time.isToday()) { // Not today and have past
            if (hasData()) {
                this.blank.setIcon(new ImageIcon(getClass().getResource("/blank_red.png")));
                this.Status.setText("已截止");
            } else {
                this.blank.setIcon(new ImageIcon(getClass().getResource("/blank_gray.png")));
                this.blank.setEnabled(false);
                this.Status.setText("無紀錄");
            }
        } else if (time.isToday()) { // Today
            this.blank.setIcon(new ImageIcon(getClass().getResource("/blank_green.png")));
            dayOfWeek.setForeground(new Color(170, 148, 0));
            if (!hasRestaurant()) {
                this.Status.setText("尚未設定餐廳");
            } else {
                if (avaliableToFill()) {
                    this.Status.setText("填寫中");
                } else {
                    this.Status.setText("已截止");
                }
            }
        } else {
            if (avaliableToFill()) {
                this.blank.setIcon(new ImageIcon(getClass().getResource("/blank_yellow.png")));
                this.Status.setText("填寫中");
            } else {
                this.blank.setIcon(new ImageIcon(getClass().getResource("/blank_white.png")));
                if (!hasRestaurant()) this.Status.setText("尚未設定餐廳");
                else this.Status.setText("已指派餐廳");
            }
        }
        //
        Date.setText(new SimpleDateFormat("yyyy/MM/dd").format(time.toDate()));
    }

    public FormInfo getForm() {
        if (FORM == null) FORM = new FormInfo(time,new ArrayList<>());
        return FORM;
    }


    public void addClickEvent(ActionListener e) {
        this.e = e;
    }

    private void clicked(ActionEvent e) {
        if (MainWINDOW != null) {
            MainWINDOW.triggered(this);
        }
        if (this.e == null) return;
        this.e.actionPerformed(e);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dayOfWeek = new JTextField();
        blank = new JButton();
        Date = new JTextField();
        Status = new JTextField();

        //======== this ========
        setPreferredSize(new Dimension(154, 240));
        setBackground(null);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //---- dayOfWeek ----
        dayOfWeek.setText("\u661f\u671f\u65e5");
        dayOfWeek.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
        dayOfWeek.setEditable(false);
        dayOfWeek.setFocusable(false);
        dayOfWeek.setBorder(null);
        dayOfWeek.setHorizontalAlignment(SwingConstants.CENTER);
        dayOfWeek.setMinimumSize(new Dimension(10, 32));
        dayOfWeek.setPreferredSize(new Dimension(20, 30));
        dayOfWeek.setOpaque(false);
        add(dayOfWeek);

        //---- blank ----
        blank.setPreferredSize(new Dimension(140, 140));
        blank.setIcon(new ImageIcon(getClass().getResource("/blank_white.png")));
        blank.setBorderPainted(false);
        blank.setOpaque(false);
        blank.setFocusPainted(false);
        blank.setContentAreaFilled(false);
        blank.setAlignmentX(0.5F);
        blank.addActionListener(e -> clicked(e));
        add(blank);

        //---- Date ----
        Date.setText("2020/09/27");
        Date.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
        Date.setEditable(false);
        Date.setFocusable(false);
        Date.setBorder(null);
        Date.setHorizontalAlignment(SwingConstants.CENTER);
        Date.setMinimumSize(new Dimension(10, 28));
        Date.setPreferredSize(new Dimension(20, 30));
        Date.setOpaque(false);
        add(Date);

        //---- Status ----
        Status.setText("\u5df2\u7d50\u7b97");
        Status.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
        Status.setEditable(false);
        Status.setFocusable(false);
        Status.setBorder(null);
        Status.setMinimumSize(new Dimension(10, 10));
        Status.setPreferredSize(new Dimension(20, 33));
        Status.setOpaque(false);
        Status.setMargin(null);
        Status.setAlignmentY(0.0F);
        Status.setHorizontalAlignment(SwingConstants.CENTER);
        Status.setSelectionEnd(1);
        Status.setSelectionStart(1);
        add(Status);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField dayOfWeek;
    private JButton blank;
    private JTextField Date;
    private JTextField Status;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
