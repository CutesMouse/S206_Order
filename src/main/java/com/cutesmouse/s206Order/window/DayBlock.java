/*
 * Created by JFormDesigner on Sun Sep 27 21:37:19 CST 2020
 */

package com.cutesmouse.s206Order.window;

import com.cutesmouse.s206Order.time.TimeStamp;
import com.cutesmouse.s206Order.utils.DisplayText;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class DayBlock extends JPanel {
    private ActionListener e;
    public static OrderWindow MainWINDOW;
    public DayBlock(TimeStamp time, boolean enabled) {
        this(time);
        blank.setEnabled(enabled);
    }
    public DayBlock(TimeStamp time) {
        initComponents();
        if (time.toDate().getTime() < System.currentTimeMillis() && !time.isToday()) {
            this.blank.setIcon(new ImageIcon(getClass().getResource("/blank.png")));
        }
        dayOfWeek.setText(DisplayText.DAYOFWEEK(time.day));
        if (time.isToday()) dayOfWeek.setForeground(new Color(170, 148, 0));
        else dayOfWeek.setForeground(new Color(0, 32, 137));
        Date.setText(new SimpleDateFormat("yyyy/MM/dd").format(time.toDate()));
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

        //======== this ========
        setPreferredSize(new Dimension(154, 210));
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
        blank.setPreferredSize(new Dimension(150, 150));
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
        Date.setMinimumSize(new Dimension(10, 32));
        Date.setPreferredSize(new Dimension(20, 30));
        Date.setOpaque(false);
        add(Date);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField dayOfWeek;
    private JButton blank;
    private JTextField Date;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
