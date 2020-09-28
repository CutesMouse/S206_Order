/*
 * Created by JFormDesigner on Sun Sep 27 16:16:47 CST 2020
 */

package com.cutesmouse.s206Order.window;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class OrderWindow extends JFrame {
    public OrderWindow() {
        initComponents();
        DayBlock db = new DayBlock();
        OrderPicker.addSubmitListener(this::orderPicker);
    }
    public void orderPicker(ActionEvent e) {
        System.out.println(OrderPicker.getTimeStamp());
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
        panel3 = new JPanel();
        panel6 = new JPanel();
        textField3 = new JTextField();
        button9 = new JButton();
        textField10 = new JTextField();
        panel7 = new JPanel();
        textField4 = new JTextField();
        button10 = new JButton();
        textField11 = new JTextField();
        panel8 = new JPanel();
        textField5 = new JTextField();
        button11 = new JButton();
        textField12 = new JTextField();
        panel9 = new JPanel();
        textField6 = new JTextField();
        button12 = new JButton();
        textField13 = new JTextField();
        panel10 = new JPanel();
        textField7 = new JTextField();
        button13 = new JButton();
        textField14 = new JTextField();
        panel11 = new JPanel();
        textField8 = new JTextField();
        button14 = new JButton();
        textField15 = new JTextField();
        panel12 = new JPanel();
        textField9 = new JTextField();
        button15 = new JButton();
        textField16 = new JTextField();

        //======== this ========
        setTitle("\u9ede\u9910\u4ecb\u9762");
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

                    //======== panel3 ========
                    {
                        panel3.setLayout(new FlowLayout());

                        //======== panel6 ========
                        {
                            panel6.setPreferredSize(new Dimension(150, 210));
                            panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));

                            //---- textField3 ----
                            textField3.setText("\u661f\u671f\u65e5");
                            textField3.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField3.setEditable(false);
                            textField3.setFocusable(false);
                            textField3.setBorder(null);
                            textField3.setHorizontalAlignment(SwingConstants.CENTER);
                            textField3.setMinimumSize(new Dimension(10, 32));
                            textField3.setPreferredSize(new Dimension(20, 30));
                            panel6.add(textField3);

                            //---- button9 ----
                            button9.setPreferredSize(new Dimension(150, 150));
                            button9.setIcon(new ImageIcon(getClass().getResource("/blank.png")));
                            button9.setBorderPainted(false);
                            button9.setOpaque(false);
                            button9.setFocusPainted(false);
                            button9.setContentAreaFilled(false);
                            panel6.add(button9);

                            //---- textField10 ----
                            textField10.setText("x/x");
                            textField10.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField10.setEditable(false);
                            textField10.setFocusable(false);
                            textField10.setBorder(null);
                            textField10.setHorizontalAlignment(SwingConstants.CENTER);
                            textField10.setMinimumSize(new Dimension(10, 32));
                            textField10.setPreferredSize(new Dimension(20, 30));
                            panel6.add(textField10);
                        }
                        panel3.add(panel6);

                        //======== panel7 ========
                        {
                            panel7.setPreferredSize(new Dimension(150, 210));
                            panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));

                            //---- textField4 ----
                            textField4.setText("\u661f\u671f\u4e00");
                            textField4.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField4.setEditable(false);
                            textField4.setFocusable(false);
                            textField4.setBorder(null);
                            textField4.setHorizontalAlignment(SwingConstants.CENTER);
                            textField4.setMinimumSize(new Dimension(10, 32));
                            textField4.setPreferredSize(new Dimension(20, 30));
                            panel7.add(textField4);

                            //---- button10 ----
                            button10.setPreferredSize(new Dimension(150, 150));
                            button10.setIcon(new ImageIcon(getClass().getResource("/blank.png")));
                            button10.setBorderPainted(false);
                            button10.setOpaque(false);
                            button10.setFocusPainted(false);
                            button10.setContentAreaFilled(false);
                            panel7.add(button10);

                            //---- textField11 ----
                            textField11.setText("x/x");
                            textField11.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField11.setEditable(false);
                            textField11.setFocusable(false);
                            textField11.setBorder(null);
                            textField11.setHorizontalAlignment(SwingConstants.CENTER);
                            textField11.setMinimumSize(new Dimension(10, 32));
                            textField11.setPreferredSize(new Dimension(20, 30));
                            panel7.add(textField11);
                        }
                        panel3.add(panel7);

                        //======== panel8 ========
                        {
                            panel8.setPreferredSize(new Dimension(150, 210));
                            panel8.setLayout(new BoxLayout(panel8, BoxLayout.Y_AXIS));

                            //---- textField5 ----
                            textField5.setText("\u661f\u671f\u4e8c");
                            textField5.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField5.setEditable(false);
                            textField5.setFocusable(false);
                            textField5.setBorder(null);
                            textField5.setHorizontalAlignment(SwingConstants.CENTER);
                            textField5.setMinimumSize(new Dimension(10, 32));
                            textField5.setPreferredSize(new Dimension(20, 30));
                            panel8.add(textField5);

                            //---- button11 ----
                            button11.setPreferredSize(new Dimension(150, 150));
                            button11.setIcon(new ImageIcon(getClass().getResource("/blank.png")));
                            button11.setBorderPainted(false);
                            button11.setOpaque(false);
                            button11.setFocusPainted(false);
                            button11.setContentAreaFilled(false);
                            panel8.add(button11);

                            //---- textField12 ----
                            textField12.setText("x/x");
                            textField12.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField12.setEditable(false);
                            textField12.setFocusable(false);
                            textField12.setBorder(null);
                            textField12.setHorizontalAlignment(SwingConstants.CENTER);
                            textField12.setMinimumSize(new Dimension(10, 32));
                            textField12.setPreferredSize(new Dimension(20, 30));
                            panel8.add(textField12);
                        }
                        panel3.add(panel8);

                        //======== panel9 ========
                        {
                            panel9.setPreferredSize(new Dimension(150, 210));
                            panel9.setLayout(new BoxLayout(panel9, BoxLayout.Y_AXIS));

                            //---- textField6 ----
                            textField6.setText("\u661f\u671f\u4e09");
                            textField6.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField6.setEditable(false);
                            textField6.setFocusable(false);
                            textField6.setBorder(null);
                            textField6.setHorizontalAlignment(SwingConstants.CENTER);
                            textField6.setMinimumSize(new Dimension(10, 32));
                            textField6.setPreferredSize(new Dimension(20, 30));
                            panel9.add(textField6);

                            //---- button12 ----
                            button12.setPreferredSize(new Dimension(150, 150));
                            button12.setIcon(new ImageIcon(getClass().getResource("/blank.png")));
                            button12.setBorderPainted(false);
                            button12.setOpaque(false);
                            button12.setFocusPainted(false);
                            button12.setContentAreaFilled(false);
                            panel9.add(button12);

                            //---- textField13 ----
                            textField13.setText("x/x");
                            textField13.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField13.setEditable(false);
                            textField13.setFocusable(false);
                            textField13.setBorder(null);
                            textField13.setHorizontalAlignment(SwingConstants.CENTER);
                            textField13.setMinimumSize(new Dimension(10, 32));
                            textField13.setPreferredSize(new Dimension(20, 30));
                            panel9.add(textField13);
                        }
                        panel3.add(panel9);

                        //======== panel10 ========
                        {
                            panel10.setPreferredSize(new Dimension(150, 210));
                            panel10.setLayout(new BoxLayout(panel10, BoxLayout.Y_AXIS));

                            //---- textField7 ----
                            textField7.setText("\u661f\u671f\u56db");
                            textField7.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField7.setEditable(false);
                            textField7.setFocusable(false);
                            textField7.setBorder(null);
                            textField7.setHorizontalAlignment(SwingConstants.CENTER);
                            textField7.setMinimumSize(new Dimension(10, 32));
                            textField7.setPreferredSize(new Dimension(20, 30));
                            panel10.add(textField7);

                            //---- button13 ----
                            button13.setPreferredSize(new Dimension(150, 150));
                            button13.setIcon(new ImageIcon(getClass().getResource("/blank.png")));
                            button13.setBorderPainted(false);
                            button13.setOpaque(false);
                            button13.setFocusPainted(false);
                            button13.setContentAreaFilled(false);
                            panel10.add(button13);

                            //---- textField14 ----
                            textField14.setText("x/x");
                            textField14.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField14.setEditable(false);
                            textField14.setFocusable(false);
                            textField14.setBorder(null);
                            textField14.setHorizontalAlignment(SwingConstants.CENTER);
                            textField14.setMinimumSize(new Dimension(10, 32));
                            textField14.setPreferredSize(new Dimension(20, 30));
                            panel10.add(textField14);
                        }
                        panel3.add(panel10);

                        //======== panel11 ========
                        {
                            panel11.setPreferredSize(new Dimension(150, 210));
                            panel11.setLayout(new BoxLayout(panel11, BoxLayout.Y_AXIS));

                            //---- textField8 ----
                            textField8.setText("\u661f\u671f\u4e94");
                            textField8.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField8.setEditable(false);
                            textField8.setFocusable(false);
                            textField8.setBorder(null);
                            textField8.setHorizontalAlignment(SwingConstants.CENTER);
                            textField8.setMinimumSize(new Dimension(10, 32));
                            textField8.setPreferredSize(new Dimension(20, 30));
                            panel11.add(textField8);

                            //---- button14 ----
                            button14.setPreferredSize(new Dimension(150, 150));
                            button14.setIcon(new ImageIcon(getClass().getResource("/blank.png")));
                            button14.setBorderPainted(false);
                            button14.setOpaque(false);
                            button14.setFocusPainted(false);
                            button14.setContentAreaFilled(false);
                            panel11.add(button14);

                            //---- textField15 ----
                            textField15.setText("x/x");
                            textField15.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField15.setEditable(false);
                            textField15.setFocusable(false);
                            textField15.setBorder(null);
                            textField15.setHorizontalAlignment(SwingConstants.CENTER);
                            textField15.setMinimumSize(new Dimension(10, 32));
                            textField15.setPreferredSize(new Dimension(20, 30));
                            panel11.add(textField15);
                        }
                        panel3.add(panel11);

                        //======== panel12 ========
                        {
                            panel12.setPreferredSize(new Dimension(150, 210));
                            panel12.setLayout(new BoxLayout(panel12, BoxLayout.Y_AXIS));

                            //---- textField9 ----
                            textField9.setText("\u661f\u671f\u516d");
                            textField9.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField9.setEditable(false);
                            textField9.setFocusable(false);
                            textField9.setBorder(null);
                            textField9.setHorizontalAlignment(SwingConstants.CENTER);
                            textField9.setMinimumSize(new Dimension(10, 32));
                            textField9.setPreferredSize(new Dimension(20, 30));
                            panel12.add(textField9);

                            //---- button15 ----
                            button15.setPreferredSize(new Dimension(150, 150));
                            button15.setIcon(new ImageIcon(getClass().getResource("/blank.png")));
                            button15.setBorderPainted(false);
                            button15.setOpaque(false);
                            button15.setFocusPainted(false);
                            button15.setContentAreaFilled(false);
                            panel12.add(button15);

                            //---- textField16 ----
                            textField16.setText("x/x");
                            textField16.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                            textField16.setEditable(false);
                            textField16.setFocusable(false);
                            textField16.setBorder(null);
                            textField16.setHorizontalAlignment(SwingConstants.CENTER);
                            textField16.setMinimumSize(new Dimension(10, 32));
                            textField16.setPreferredSize(new Dimension(20, 30));
                            panel12.add(textField16);
                        }
                        panel3.add(panel12);
                    }
                    panel2.add(panel3);
                    panel3.setBounds(25, 70, 1135, 235);

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
                settings.setViewportView(panel2);
            }
            tabbedPane1.addTab("\u8a2d\u5b9a", settings);
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
    private JPanel panel3;
    private JPanel panel6;
    private JTextField textField3;
    private JButton button9;
    private JTextField textField10;
    private JPanel panel7;
    private JTextField textField4;
    private JButton button10;
    private JTextField textField11;
    private JPanel panel8;
    private JTextField textField5;
    private JButton button11;
    private JTextField textField12;
    private JPanel panel9;
    private JTextField textField6;
    private JButton button12;
    private JTextField textField13;
    private JPanel panel10;
    private JTextField textField7;
    private JButton button13;
    private JTextField textField14;
    private JPanel panel11;
    private JTextField textField8;
    private JButton button14;
    private JTextField textField15;
    private JPanel panel12;
    private JTextField textField9;
    private JButton button15;
    private JTextField textField16;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
