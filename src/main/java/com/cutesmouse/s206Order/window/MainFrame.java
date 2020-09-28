/*
 * Created by JFormDesigner on Sun Sep 27 08:33:31 CST 2020
 */

package com.cutesmouse.s206Order.window;

import java.awt.event.*;

import com.cutesmouse.s206Order.Main;
import com.cutesmouse.s206Order.restaurant.RestaurantBuilder;
import com.cutesmouse.s206Order.utils.DisplayText;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        initComponents();
        loadDate();
    }

    private void loadDate() {
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        int dayofWeek = c.get(Calendar.DAY_OF_WEEK);
        date.setText(new SimpleDateFormat("yyyy年MM月dd日 第"+c.get(Calendar.WEEK_OF_MONTH)+"週 "+ DisplayText.DAYOFWEEK(dayofWeek)).format(new Date()));
    }

    private void WindowClosing(WindowEvent e) {
        Main.end();
        dispose();
        System.exit(0);
    }

    private void editRestaurants(ActionEvent e) {
        RestaurantManager manager = new RestaurantManager();
        manager.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        files = new JMenu();
        output = new JMenuItem();
        rest = new JMenu();
        editRest = new JMenuItem();
        stu = new JMenu();
        menuItem1 = new JMenuItem();
        order = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        date = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new Color(153, 255, 153));
        setTitle("S206 \u591c\u8f14\u9ede\u9910\u7cfb\u7d71");
        setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== files ========
            {
                files.setText(" \u6a94\u6848(F)");

                //---- output ----
                output.setText("\u532f\u51fa\u672c\u9031\u8cc7\u6599");
                files.add(output);
            }
            menuBar1.add(files);

            //======== rest ========
            {
                rest.setText(" \u9910\u5ef3(R) ");

                //---- editRest ----
                editRest.setText("\u7de8\u8f2f\u9910\u5ef3");
                editRest.addActionListener(e -> editRestaurants(e));
                rest.add(editRest);
            }
            menuBar1.add(rest);

            //======== stu ========
            {
                stu.setText(" \u5b78\u751f(S) ");

                //---- menuItem1 ----
                menuItem1.setText("\u7de8\u8f2f\u5b78\u751f");
                stu.add(menuItem1);
            }
            menuBar1.add(stu);

            //======== order ========
            {
                order.setText(" \u9ede\u9910(O) ");

                //---- menuItem2 ----
                menuItem2.setText("\u958b\u555f\u9ede\u9910\u8996\u7a97");
                order.add(menuItem2);

                //---- menuItem3 ----
                menuItem3.setText("\u6307\u5b9a\u5167\u5bb9");
                order.add(menuItem3);
            }
            menuBar1.add(order);
        }
        setJMenuBar(menuBar1);

        //---- date ----
        date.setForeground(Color.black);
        date.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 22));
        date.setEditable(false);
        date.setBorder(null);
        date.setFocusable(false);
        date.setRequestFocusEnabled(false);
        date.setText("xxxx\u5e74xx\u6708xx\u65e5 \u7b2cx\u9031 \u661f\u671fx");
        contentPane.add(date);
        date.setBounds(0, -35, 400, 100);

        contentPane.setPreferredSize(new Dimension(1135, 640));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu files;
    private JMenuItem output;
    private JMenu rest;
    private JMenuItem editRest;
    private JMenu stu;
    private JMenuItem menuItem1;
    private JMenu order;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JTextField date;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
