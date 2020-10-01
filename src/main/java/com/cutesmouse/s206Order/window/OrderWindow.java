/*
 * Created by JFormDesigner on Sun Sep 27 16:16:47 CST 2020
 */

package com.cutesmouse.s206Order.window;

import java.awt.event.*;

import com.cutesmouse.s206Order.Main;
import com.cutesmouse.s206Order.form.FormInfo;
import com.cutesmouse.s206Order.form.FormManager;
import com.cutesmouse.s206Order.form.MealData;
import com.cutesmouse.s206Order.formWindow.FormPanel;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.restaurant.RestaurantBuilder;
import com.cutesmouse.s206Order.time.TimeStamp;
import com.cutesmouse.s206Order.utils.DayOrderedManager;
import com.cutesmouse.s206Order.utils.DisplayText;
import com.cutesmouse.s206Order.utils.ValueSearch;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

/**
 * @author CutesMouse
 */
public class OrderWindow extends JFrame {
    private int weekOffset;
    private DayBlock SELECTED;
    public OrderWindow() {
        DayBlock.MainWINDOW = this;
        initComponents();
        //OrderPanel.add(new FormPanel());
        weekOffset = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.WEEK_OF_MONTH);
        loadNest();
        restaurants.addListSelectionListener(this::valueChanged);
        refresh(null);
        loadOrderPanel();
        scrollPane2.getVerticalScrollBar().setUnitIncrement(10);
    }
    public void triggered(DayBlock block) {
        /*
        getData.setEnabled(b);
        setRestaurant.setEnabled(b);
        status.setEnabled(b);
        restaurantList.setEnabled(b);
         */
        for (Component c : daysNest.getComponents()) {
            c.setBackground(null);
        }
        SELECTED = block;
        loadButton();
        block.setBackground(new Color(180, 255, 156));
    }
    private void loadButton() {
        DayBlock block = SELECTED;
        if (block == null) return;
        restaurantList.setEnabled(block.hasRestaurant());
        getData.setEnabled(block.hasData());
        status.setIcon(new ImageIcon(getClass().getResource("/toggleFill_"+ (block.avaliableToFill() ? "On" : "Off") + ".png")));
        if (block.getTimestamp().toDate().getTime() < System.currentTimeMillis() && !block.getTimestamp().isToday()) {
            setRestaurant.setEnabled(false);
            status.setEnabled(false);
        } else {
            setRestaurant.setEnabled(true);
            status.setEnabled(block.hasRestaurant());
        }

        getData.setEnabled(SELECTED.hasData());
    }
    private void setButtonStatus(boolean b) {
        getData.setEnabled(b);
        setRestaurant.setEnabled(b);
        status.setEnabled(b);
        restaurantList.setEnabled(b);
    }
    private void loadNest() {
        daysNest.removeAll();
        SELECTED = null;
        setButtonStatus(false);
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        int week = weekOffset;
        cal.set(Calendar.WEEK_OF_MONTH, week);
        week = cal.get(Calendar.WEEK_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        int from = 1;
        int end = 7;
        if (week == 1) {
            from = cal.get(Calendar.DAY_OF_WEEK);
            end = 7;
        }
        if (week == cal.getActualMaximum(Calendar.WEEK_OF_MONTH)) {
            from = 1;
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
            cal.set(Calendar.DAY_OF_MONTH, 0);
            end = cal.get(Calendar.DAY_OF_WEEK);
        }
        if (from > 1) {
            for (int i = 1; i < from; i++) {
                daysNest.add(new DayBlock(new TimeStamp(year, month, week, i), true));
            }
        }
        for (int i = from; i <= end; i++) {
            daysNest.add(new DayBlock(new TimeStamp(year, month, week, i)));
        }
        if (end != 7) {
            for (int i = end + 1; i <= 7; i++) {
                daysNest.add(new DayBlock(new TimeStamp(year, month, week, i), true));
            }
        }
        daysNest.updateUI();
    }
    private void loadOrderPanel() {
        forms.removeAll();
        for (FormPanel m : FormManager.getPanels()) {
            forms.add(m);
        }
        forms.updateUI();
    }
    private void next_week(ActionEvent e) {
        weekOffset++;
        loadNest();
    }

    private void last_week(ActionEvent e) {
        weekOffset--;
        loadNest();
    }

    private void refresh(ActionEvent e) {
        remove.setEnabled(false);
        edit.setEnabled(false);
        restaurants.setListData(Restaurant.RESTAURANTS.toArray());
    }

    private void add(ActionEvent e) {
        new CreateResturant(new RestaurantBuilder()).setVisible(true);
    }

    private void remove(ActionEvent e) {
        Object o = restaurants.getSelectedValue();
        if (o == null) {
            remove.setEnabled(false);
            edit.setEnabled(false);
            return;
        }
        Restaurant.removeRestaurant(((Restaurant) o));
        refresh(e);
    }

    private void edit(ActionEvent e) {
        Object o = restaurants.getSelectedValue();
        if (o == null) {
            remove.setEnabled(false);
            edit.setEnabled(false);
            return;
        }
        new CreateResturant(((Restaurant) o)).setVisible(true);
    }

    private void valueChanged(ListSelectionEvent e) {
        Object o = restaurants.getSelectedValue();
        if (o == null) {
            remove.setEnabled(false);
            edit.setEnabled(false);
        } else {
            remove.setEnabled(true);
            edit.setEnabled(true);
        }
    }

    private void sort(ActionEvent e) {
        ArrayList<Restaurant> c = new ArrayList<>(Restaurant.RESTAURANTS);
        Collections.sort(c, Comparator.comparing(p -> p.name));
        restaurants.setListData(c.toArray());
        remove.setEnabled(false);
        edit.setEnabled(false);
    }
    private void setToday(ActionEvent e) {
        weekOffset = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.WEEK_OF_MONTH);
        loadNest();
        for (Component cp : daysNest.getComponents()) {
            if (!(cp instanceof DayBlock)) continue;
            if (((DayBlock) cp).getTimestamp().isToday()) triggered(((DayBlock) cp));
        }
    }
    private void thisWindowClosing(WindowEvent e) {
        Main.end();
        dispose();
        System.exit(0);
    }
    private void ToggleStatus(ActionEvent e) {
        if (SELECTED == null) return;
        if (SELECTED.getForm().getRestaurants().size() == 0) return;
        SELECTED.getForm().setToggled(!SELECTED.getForm().isToggled());
        DayBlock ds = SELECTED;
        loadNest();
        triggered(ds);
        loadButton();
        loadOrderPanel();
    }

    private void AddRestaurantToForm(ActionEvent e) {
        if (SELECTED == null) return;
        Object o = restaurants.getSelectedValue();
        if (!(o instanceof Restaurant)) {
            JOptionPane.showMessageDialog(this,"你沒有選擇任何餐廳!","錯誤",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Restaurant r = ((Restaurant) o);
        if (SELECTED.getForm().getRestaurants().contains(r)) {
            JOptionPane.showMessageDialog(this,"這個項目已經存在!","錯誤",JOptionPane.WARNING_MESSAGE);
            return;
        }
        SELECTED.getForm().appendRestaurant(r);
        DayBlock ds = SELECTED;
        loadNest();
        triggered(ds);
        loadButton();
    }

    public ArrayList<MealData> getMealDatas() {
        ArrayList<MealData> mds = new ArrayList<>();
        for (Component c : forms.getComponents()) {
            if (!(c instanceof FormPanel)) continue;
            FormPanel fp = ((FormPanel) c);
            mds.addAll(fp.getMealDatas());
        }
        return mds;
    }

    private void submit(ActionEvent e) {
        if (sid.getText().isEmpty() || !sid.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this,"請輸入學號!","錯誤",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nid.getText().isEmpty() || !nid.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this,"請輸入座號!","錯誤",JOptionPane.ERROR_MESSAGE);
            return;
        }
        MealData data = ValueSearch.search(getMealDatas(), p -> p.getAmount() <= 0);
        if (data != null) {
            JOptionPane.showMessageDialog(this,"點餐的數量出現非數字字元","錯誤",JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        OrderPanel = new JPanel();
        des_formInfo4 = new JTextField();
        des_formInfo2 = new JTextField();
        nid = new JTextField();
        des_formInfo3 = new JTextField();
        sid = new JTextField();
        scrollPane2 = new JScrollPane();
        forms = new JPanel();
        button1 = new JButton();
        querySingle = new JScrollPane();
        queryAll = new JScrollPane();
        panel4 = new JPanel();
        timeStampPicker1 = new TimeStampPicker();
        settings = new JScrollPane();
        settingPanel = new JPanel();
        des_formInfo = new JTextField();
        daysNest = new JPanel();
        nextPage = new JButton();
        lastPage = new JButton();
        Today = new JButton();
        restaurantPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        restaurants = new JList();
        edit = new JButton();
        add = new JButton();
        remove = new JButton();
        refresh = new JButton();
        refresh2 = new JButton();
        restaurantList = new JButton();
        getData = new JButton();
        status = new JButton();
        setRestaurant = new JButton();

        //======== this ========
        setTitle("\u9ede\u9910\u4ecb\u9762");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 18));

            //======== OrderPanel ========
            {
                OrderPanel.setLayout(null);

                //---- des_formInfo4 ----
                des_formInfo4.setText("\u5fb7\u5149\u4e2d\u5b78 S206 \u9ede\u9910\u7cfb\u7d71");
                des_formInfo4.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                des_formInfo4.setEditable(false);
                des_formInfo4.setFocusable(false);
                des_formInfo4.setBorder(null);
                des_formInfo4.setAutoscrolls(false);
                des_formInfo4.setHorizontalAlignment(SwingConstants.CENTER);
                des_formInfo4.setPreferredSize(new Dimension(230, 20));
                OrderPanel.add(des_formInfo4);
                des_formInfo4.setBounds(0, 0, 1168, 50);

                //---- des_formInfo2 ----
                des_formInfo2.setText("\u5ea7\u865f: ");
                des_formInfo2.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                des_formInfo2.setEditable(false);
                des_formInfo2.setFocusable(false);
                des_formInfo2.setBorder(null);
                des_formInfo2.setAutoscrolls(false);
                OrderPanel.add(des_formInfo2);
                des_formInfo2.setBounds(415, 60, 65, 30);

                //---- nid ----
                nid.setPreferredSize(new Dimension(100, 30));
                nid.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
                OrderPanel.add(nid);
                nid.setBounds(485, 60, 100, 30);

                //---- des_formInfo3 ----
                des_formInfo3.setText("\u5b78\u865f: ");
                des_formInfo3.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                des_formInfo3.setEditable(false);
                des_formInfo3.setFocusable(false);
                des_formInfo3.setBorder(null);
                des_formInfo3.setAutoscrolls(false);
                OrderPanel.add(des_formInfo3);
                des_formInfo3.setBounds(605, 60, 60, 30);

                //---- sid ----
                sid.setPreferredSize(new Dimension(100, 30));
                sid.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
                OrderPanel.add(sid);
                sid.setBounds(670, 60, 100, 30);

                //======== scrollPane2 ========
                {
                    scrollPane2.setBorder(null);

                    //======== forms ========
                    {
                        forms.setLayout(new BoxLayout(forms, BoxLayout.Y_AXIS));
                    }
                    scrollPane2.setViewportView(forms);
                }
                OrderPanel.add(scrollPane2);
                scrollPane2.setBounds(0, 110, 1165, 520);

                //---- button1 ----
                button1.setIcon(new ImageIcon(getClass().getResource("/submit.png")));
                button1.setOpaque(false);
                button1.setBorderPainted(false);
                button1.setFocusPainted(false);
                button1.setContentAreaFilled(false);
                button1.addActionListener(e -> submit(e));
                OrderPanel.add(button1);
                button1.setBounds(1070, 60, 82, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < OrderPanel.getComponentCount(); i++) {
                        Rectangle bounds = OrderPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = OrderPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    OrderPanel.setMinimumSize(preferredSize);
                    OrderPanel.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("\u9ede\u9910", OrderPanel);
            tabbedPane1.addTab("\u500b\u4eba\u67e5\u8a62", querySingle);

            //======== queryAll ========
            {

                //======== panel4 ========
                {
                    panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
                    panel4.add(timeStampPicker1);
                }
                queryAll.setViewportView(panel4);
            }
            tabbedPane1.addTab("\u5168\u73ed\u67e5\u8a62", queryAll);

            //======== settings ========
            {

                //======== settingPanel ========
                {
                    settingPanel.setLayout(null);

                    //---- des_formInfo ----
                    des_formInfo.setText("\u8868\u55ae\u5167\u5bb9");
                    des_formInfo.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                    des_formInfo.setEditable(false);
                    des_formInfo.setFocusable(false);
                    des_formInfo.setBorder(null);
                    settingPanel.add(des_formInfo);
                    des_formInfo.setBounds(25, 30, 105, 40);

                    //======== daysNest ========
                    {
                        daysNest.setPreferredSize(new Dimension(10, 243));
                        daysNest.setLayout(new FlowLayout());
                    }
                    settingPanel.add(daysNest);
                    daysNest.setBounds(25, 70, 1135, 235);

                    //---- nextPage ----
                    nextPage.setIcon(new ImageIcon(getClass().getResource("/left_next.png")));
                    nextPage.setBorder(null);
                    nextPage.setOpaque(false);
                    nextPage.setBorderPainted(false);
                    nextPage.setFocusPainted(false);
                    nextPage.setContentAreaFilled(false);
                    nextPage.addActionListener(e -> next_week(e));
                    settingPanel.add(nextPage);
                    nextPage.setBounds(172, 35, 30, 30);

                    //---- lastPage ----
                    lastPage.setIcon(new ImageIcon(getClass().getResource("/last_page.png")));
                    lastPage.setBorder(null);
                    lastPage.setOpaque(false);
                    lastPage.setBorderPainted(false);
                    lastPage.setFocusPainted(false);
                    lastPage.setContentAreaFilled(false);
                    lastPage.addActionListener(e -> last_week(e));
                    settingPanel.add(lastPage);
                    lastPage.setBounds(135, 35, 30, 30);

                    //---- Today ----
                    Today.setIcon(new ImageIcon(getClass().getResource("/today.png")));
                    Today.setBorder(null);
                    Today.setOpaque(false);
                    Today.setBorderPainted(false);
                    Today.setFocusPainted(false);
                    Today.setContentAreaFilled(false);
                    Today.addActionListener(e -> setToday(e));
                    settingPanel.add(Today);
                    Today.setBounds(209, 35, 30, 30);

                    //======== restaurantPanel ========
                    {
                        restaurantPanel.setLayout(null);

                        //======== scrollPane1 ========
                        {

                            //---- restaurants ----
                            restaurants.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
                            scrollPane1.setViewportView(restaurants);
                        }
                        restaurantPanel.add(scrollPane1);
                        scrollPane1.setBounds(0, 20, 1080, 265);

                        //---- edit ----
                        edit.setForeground(Color.red);
                        edit.setIcon(new ImageIcon(getClass().getResource("/edit.png")));
                        edit.setBorderPainted(false);
                        edit.setContentAreaFilled(false);
                        edit.setEnabled(false);
                        edit.addActionListener(e -> edit(e));
                        restaurantPanel.add(edit);
                        edit.setBounds(1090, 95, 30, 30);

                        //---- add ----
                        add.setForeground(new Color(0, 153, 0));
                        add.setIcon(new ImageIcon(getClass().getResource("/add.png")));
                        add.setBorder(null);
                        add.setOpaque(false);
                        add.setBorderPainted(false);
                        add.setContentAreaFilled(false);
                        add.setFocusPainted(false);
                        add.addActionListener(e -> add(e));
                        restaurantPanel.add(add);
                        add.setBounds(1090, 25, 30, 30);

                        //---- remove ----
                        remove.setForeground(Color.red);
                        remove.setIcon(new ImageIcon(getClass().getResource("/minus.png")));
                        remove.setBorderPainted(false);
                        remove.setContentAreaFilled(false);
                        remove.setEnabled(false);
                        remove.setFocusPainted(false);
                        remove.addActionListener(e -> remove(e));
                        restaurantPanel.add(remove);
                        remove.setBounds(1090, 60, 30, 30);

                        //---- refresh ----
                        refresh.setForeground(Color.red);
                        refresh.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
                        refresh.setBorderPainted(false);
                        refresh.setContentAreaFilled(false);
                        refresh.setFocusPainted(false);
                        refresh.addActionListener(e -> refresh(e));
                        restaurantPanel.add(refresh);
                        refresh.setBounds(1090, 130, 30, 30);

                        //---- refresh2 ----
                        refresh2.setForeground(Color.red);
                        refresh2.setIcon(new ImageIcon(getClass().getResource("/sort.png")));
                        refresh2.setBorderPainted(false);
                        refresh2.setContentAreaFilled(false);
                        refresh2.setFocusPainted(false);
                        refresh2.addActionListener(e -> sort(e));
                        restaurantPanel.add(refresh2);
                        refresh2.setBounds(1090, 240, 30, 30);
                    }
                    settingPanel.add(restaurantPanel);
                    restaurantPanel.setBounds(25, 350, 1135, 285);

                    //---- restaurantList ----
                    restaurantList.setIcon(new ImageIcon(getClass().getResource("/viewRestaurants_On.png")));
                    restaurantList.setBorder(null);
                    restaurantList.setOpaque(false);
                    restaurantList.setBorderPainted(false);
                    restaurantList.setFocusPainted(false);
                    restaurantList.setContentAreaFilled(false);
                    restaurantList.setPreferredSize(new Dimension(100, 30));
                    settingPanel.add(restaurantList);
                    restaurantList.setBounds(25, 320, 100, 30);

                    //---- getData ----
                    getData.setIcon(new ImageIcon(getClass().getResource("/getData_Off.png")));
                    getData.setBorder(null);
                    getData.setOpaque(false);
                    getData.setBorderPainted(false);
                    getData.setFocusPainted(false);
                    getData.setContentAreaFilled(false);
                    getData.setPreferredSize(new Dimension(100, 30));
                    settingPanel.add(getData);
                    getData.setBounds(142, 320, 100, 30);

                    //---- status ----
                    status.setIcon(new ImageIcon(getClass().getResource("/toggleFill_Off.png")));
                    status.setBorder(null);
                    status.setOpaque(false);
                    status.setBorderPainted(false);
                    status.setFocusPainted(false);
                    status.setContentAreaFilled(false);
                    status.setPreferredSize(new Dimension(100, 30));
                    status.addActionListener(e -> ToggleStatus(e));
                    settingPanel.add(status);
                    status.setBounds(1045, 320, 100, 30);

                    //---- setRestaurant ----
                    setRestaurant.setIcon(new ImageIcon(getClass().getResource("/AddRestaurant.png")));
                    setRestaurant.setBorder(null);
                    setRestaurant.setOpaque(false);
                    setRestaurant.setBorderPainted(false);
                    setRestaurant.setFocusPainted(false);
                    setRestaurant.setContentAreaFilled(false);
                    setRestaurant.setPreferredSize(new Dimension(100, 30));
                    setRestaurant.addActionListener(e -> AddRestaurantToForm(e));
                    settingPanel.add(setRestaurant);
                    setRestaurant.setBounds(259, 320, 166, 30);
                }
                settings.setViewportView(settingPanel);
            }
            tabbedPane1.addTab("\u83dc\u55ae\u8a2d\u5b9a", settings);
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
    private JPanel OrderPanel;
    private JTextField des_formInfo4;
    private JTextField des_formInfo2;
    private JTextField nid;
    private JTextField des_formInfo3;
    private JTextField sid;
    private JScrollPane scrollPane2;
    private JPanel forms;
    private JButton button1;
    private JScrollPane querySingle;
    private JScrollPane queryAll;
    private JPanel panel4;
    private TimeStampPicker timeStampPicker1;
    private JScrollPane settings;
    private JPanel settingPanel;
    private JTextField des_formInfo;
    private JPanel daysNest;
    private JButton nextPage;
    private JButton lastPage;
    private JButton Today;
    private JPanel restaurantPanel;
    private JScrollPane scrollPane1;
    private JList restaurants;
    private JButton edit;
    private JButton add;
    private JButton remove;
    private JButton refresh;
    private JButton refresh2;
    private JButton restaurantList;
    private JButton getData;
    private JButton status;
    private JButton setRestaurant;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
