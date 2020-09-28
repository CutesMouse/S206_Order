/*
 * Created by JFormDesigner on Sun Sep 27 21:37:19 CST 2020
 */

package com.cutesmouse.s206Order.window;

import java.awt.*;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class DayBlock extends JPanel {
    public DayBlock() {

    }
    public DayBlock(int week, String date) {

        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        textField3 = new JTextField();
        button9 = new JButton();
        textField4 = new JTextField();

        //======== this ========
        setPreferredSize(new Dimension(150, 180));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //---- textField3 ----
        textField3.setText("\u661f\u671f\u65e5");
        textField3.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
        textField3.setEditable(false);
        textField3.setFocusable(false);
        textField3.setBorder(null);
        textField3.setHorizontalAlignment(SwingConstants.CENTER);
        textField3.setMinimumSize(new Dimension(10, 32));
        textField3.setPreferredSize(new Dimension(20, 30));
        add(textField3);

        //---- button9 ----
        button9.setPreferredSize(new Dimension(150, 150));
        button9.setIcon(new ImageIcon(getClass().getResource("/blank.png")));
        button9.setBorderPainted(false);
        button9.setOpaque(false);
        button9.setFocusPainted(false);
        button9.setContentAreaFilled(false);
        add(button9);

        //---- textField4 ----
        textField4.setText("2020/09/27");
        textField4.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
        textField4.setEditable(false);
        textField4.setFocusable(false);
        textField4.setBorder(null);
        textField4.setHorizontalAlignment(SwingConstants.CENTER);
        textField4.setMinimumSize(new Dimension(10, 32));
        textField4.setPreferredSize(new Dimension(20, 30));
        add(textField4);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField textField3;
    private JButton button9;
    private JTextField textField4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
