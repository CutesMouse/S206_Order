/*
 * Created by JFormDesigner on Sun Sep 27 09:45:35 CST 2020
 */

package com.cutesmouse.s206Order.window;

import java.awt.*;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class AddMealPanel extends JPanel {
    public AddMealPanel() {
        initComponents();
    }
    public String getMealName() {
        return mealName.getText();
    }
    public String getMealPrice() {
        return mealPrice.getText();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        textField1 = new JTextField();
        textField2 = new JTextField();
        mealName = new JTextField();
        mealPrice = new JTextField();

        //======== this ========
        setLayout(null);

        //---- textField1 ----
        textField1.setText("\u9910\u9ede\u540d\u7a31:");
        textField1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        textField1.setBorder(null);
        textField1.setEditable(false);
        add(textField1);
        textField1.setBounds(new Rectangle(new Point(0, 5), textField1.getPreferredSize()));

        //---- textField2 ----
        textField2.setText("\u50f9\u683c:");
        textField2.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        textField2.setBorder(null);
        textField2.setEditable(false);
        textField2.setHorizontalAlignment(SwingConstants.CENTER);
        add(textField2);
        textField2.setBounds(0, 45, 77, 27);

        //---- mealName ----
        mealName.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
        add(mealName);
        mealName.setBounds(95, 5, 215, 27);

        //---- mealPrice ----
        mealPrice.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
        add(mealPrice);
        mealPrice.setBounds(95, 45, 215, 27);

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
    private JTextField textField1;
    private JTextField textField2;
    private JTextField mealName;
    private JTextField mealPrice;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
