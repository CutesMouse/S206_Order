/*
 * Created by JFormDesigner on Wed Sep 30 20:19:57 CST 2020
 */

package com.cutesmouse.s206Order.formWindow;

import com.cutesmouse.s206Order.form.FormInfo;
import com.cutesmouse.s206Order.form.MealData;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.utils.DisplayText;
import com.cutesmouse.s206Order.utils.ValueSearch;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author CutesMouse
 */
public class FormPanel extends JPanel {
    private final FormInfo info;
    public FormPanel(FormInfo info) {
        this.info = info;
        initComponents();
        des_formInfo.setText(new SimpleDateFormat("yyyy年MM月dd日 ")
                .format(info.getTime().toDate())+ DisplayText.DAYOFWEEK(info.getTime().day));
        this.scrollPane1.getVerticalScrollBar().setUnitIncrement(10);
        for (Restaurant r : info.getRestaurants()) {
            rests.add(new RestaurantPanel(r));
        }
    }

    public FormInfo getInfo() {
        return info;
    }

    public ArrayList<MealData> getMealDatas() {
        ArrayList<MealData> mds = new ArrayList<>();
        for (Component c : rests.getComponents()) {
            if (!(c instanceof RestaurantPanel)) continue;
            mds.addAll(((RestaurantPanel) c).getMealDatas());
        }
        return mds;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        des_formInfo = new JTextField();
        scrollPane1 = new JScrollPane();
        rests = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(1165, 485));
        setLayout(null);

        //---- des_formInfo ----
        des_formInfo.setText("xxxx\u5e74xx\u6708xx\u65e5 \u661f\u671fx");
        des_formInfo.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
        des_formInfo.setEditable(false);
        des_formInfo.setFocusable(false);
        des_formInfo.setBorder(null);
        des_formInfo.setAutoscrolls(false);
        add(des_formInfo);
        des_formInfo.setBounds(15, 0, 345, 45);

        //======== scrollPane1 ========
        {
            scrollPane1.setBorder(null);

            //======== rests ========
            {
                rests.setBorder(null);
                rests.setLayout(new BoxLayout(rests, BoxLayout.Y_AXIS));
            }
            scrollPane1.setViewportView(rests);
        }
        add(scrollPane1);
        scrollPane1.setBounds(45, 50, 1055, 435);

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
    private JTextField des_formInfo;
    private JScrollPane scrollPane1;
    private JPanel rests;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
