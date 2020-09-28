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
    public DayBlock(TimeStamp time, boolean enabled) {
        this(time);
        button9.setEnabled(enabled);
    }
    public DayBlock(TimeStamp time) {
        initComponents();
        dayOfWeek.setText(DisplayText.DAYOFWEEK(time.day));
        if (time.isToday()) dayOfWeek.setForeground(new Color(170, 148, 0));
        else dayOfWeek.setForeground(new Color(0, 32, 137));
        Date.setText(new SimpleDateFormat("yyyy/MM/dd").format(time.toDate()));
    }
    public void addClickEvent(ActionListener e) {
        this.e = e;
    }

    private void clicked(ActionEvent e) {
        if (this.e == null) return;
        this.e.actionPerformed(e);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dayOfWeek = new JTextField();
        button9 = new JButton();
        Date = new JTextField();

        //======== this ========
        setPreferredSize(new Dimension(150, 210));
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
        add(dayOfWeek);

        //---- button9 ----
        button9.setPreferredSize(new Dimension(150, 150));
        button9.setIcon(new ImageIcon(getClass().getResource("/blank.png")));
        button9.setBorderPainted(false);
        button9.setOpaque(false);
        button9.setFocusPainted(false);
        button9.setContentAreaFilled(false);
        button9.addActionListener(e -> clicked(e));
        add(button9);

        //---- Date ----
        Date.setText("2020/09/27");
        Date.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
        Date.setEditable(false);
        Date.setFocusable(false);
        Date.setBorder(null);
        Date.setHorizontalAlignment(SwingConstants.CENTER);
        Date.setMinimumSize(new Dimension(10, 32));
        Date.setPreferredSize(new Dimension(20, 30));
        add(Date);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField dayOfWeek;
    private JButton button9;
    private JTextField Date;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
