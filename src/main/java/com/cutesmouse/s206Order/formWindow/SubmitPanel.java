/*
 * Created by JFormDesigner on Thu Oct 01 08:14:11 CST 2020
 */

package com.cutesmouse.s206Order.formWindow;

import com.cutesmouse.s206Order.form.MealData;
import com.cutesmouse.s206Order.utils.DisplayText;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class SubmitPanel extends JScrollPane {
    public SubmitPanel(ArrayList<MealData> mds) {
        initComponents();
        mds.sort(Comparator.comparingLong(p -> p.getTime().toDate().getTime()));
        for (MealData md : mds) {
            c.add(new SingleSubmit(md));
        }
        c.add(new TotalColumn(mds));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        c = new JPanel();

        //======== this ========
        setOpaque(false);
        setBorder(null);

        //======== c ========
        {
            c.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            c.setBorder(null);
            c.setFocusable(false);
            c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        }
        setViewportView(c);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel c;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
