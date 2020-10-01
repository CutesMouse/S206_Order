/*
 * Created by JFormDesigner on Thu Oct 01 09:07:08 CST 2020
 */

package com.cutesmouse.s206Order.formWindow;

import com.cutesmouse.s206Order.form.MealData;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class TotalColumn extends JPanel {
    public TotalColumn(ArrayList<MealData> mds) {
        initComponents();
        price.setText(mds.stream().mapToInt(MealData::getPrice).sum()+"");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        item = new JTextField();
        price = new JTextField();
        separator1 = new JSeparator();

        //======== this ========
        setLayout(null);

        //---- item ----
        item.setText("\u5c0f\u8a08");
        item.setEditable(false);
        item.setBorder(null);
        item.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(item);
        item.setBounds(10, 20, 140, 30);

        //---- price ----
        price.setText("100");
        price.setEditable(false);
        price.setBorder(null);
        price.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(price);
        price.setBounds(520, 20, 60, 30);
        add(separator1);
        separator1.setBounds(0, 10, 585, 8);

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
    private JTextField item;
    private JTextField price;
    private JSeparator separator1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
