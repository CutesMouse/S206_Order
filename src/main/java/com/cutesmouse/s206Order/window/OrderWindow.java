/*
 * Created by JFormDesigner on Sun Sep 27 16:16:47 CST 2020
 */

package com.cutesmouse.s206Order.window;

import java.awt.event.*;
import javax.swing.event.*;

import com.cutesmouse.s206Order.Main;
import com.cutesmouse.s206Order.form.FormInfo;
import com.cutesmouse.s206Order.form.FormManager;
import com.cutesmouse.s206Order.form.MealData;
import com.cutesmouse.s206Order.formWindow.FormPanel;
import com.cutesmouse.s206Order.formWindow.SubmitPanel;
import com.cutesmouse.s206Order.restaurant.Meal;
import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.restaurant.RestaurantBuilder;
import com.cutesmouse.s206Order.student.OrderedItem;
import com.cutesmouse.s206Order.student.Student;
import com.cutesmouse.s206Order.time.TimeStamp;
import com.cutesmouse.s206Order.time.TimeStampPickerEvent;
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
    private static final int DEFAULT_WIDTH = 1170;
    private static final int DEFAULT_HEIGHT = 690;
    private DayBlock SELECTED;
    public OrderWindow() {
        DayBlock.MainWINDOW = this;
        initComponents();
        weekOffset = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.WEEK_OF_MONTH);
        loadNest();
        restaurants.addListSelectionListener(this::valueChanged);
        refresh(null);
        timeStampPicker1.addSubmitListener(this::TimeStampPicker);
        loadOrderPanel();
        scrollPane2.getVerticalScrollBar().setUnitIncrement(10);
        Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();
        double scale = 1.0;
        if (sc.width < DEFAULT_WIDTH) {
            scale = (double) sc.width / DEFAULT_WIDTH;
        }
        if (sc.height < DEFAULT_HEIGHT* scale) {
            scale = (double) sc.height / DEFAULT_HEIGHT;
        }
        if (scale < 1.0) {
            tabbedPane1.setSize((int)(DEFAULT_WIDTH*scale),(int)(DEFAULT_HEIGHT*scale));
            size_display.setText(Double.toString(Math.round(scale * 10) / 10.0));
        }
    }
    public void TimeStampPicker(boolean b, TimeStampPicker e) {
        if (b) {
            allQueryResult.removeAll();
            allQueryResult.add(new ShowData(e.getTimeStamps()));
            allQueryResult.updateUI();
            return;
        }
        TimeStamp time = timeStampPicker1.getTimeStamp();
        if (time == null) return;
        allQueryResult.removeAll();
        allQueryResult.add(new ShowData(time));
        allQueryResult.updateUI();
    }
    public void triggered(DayBlock block) {
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
                DayBlock add = new DayBlock(new TimeStamp(year, month, week, i), true);
                daysNest.add(add);
            }
        }
        for (int i = from; i <= end; i++) {
            DayBlock add = new DayBlock(new TimeStamp(year, month, week, i));
            daysNest.add(add);
        }
        if (end != 7) {
            for (int i = end + 1; i <= 7; i++) {
                DayBlock add = new DayBlock(new TimeStamp(year, month, week, i), true);
                daysNest.add(add);
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
        for (Component c : daysNest.getComponents()) {
            if (!(c instanceof DayBlock)) return;
            if (c.equals(ds)) {
                triggered(((DayBlock) c));
                break;
            }
        }
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
        for (Component c : daysNest.getComponents()) {
            if (!(c instanceof DayBlock)) return;
            if (c.equals(ds)) {
                triggered(((DayBlock) c));
                break;
            }
        }
        loadOrderPanel();
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
        if (nid.getText().isEmpty() || !nid.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this,"請輸入座號!","錯誤",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (sid.getText().isEmpty() || !sid.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this,"請輸入學號!","錯誤",JOptionPane.ERROR_MESSAGE);
            return;
        }
        ArrayList<MealData> meals = getMealDatas();
        if (meals.size() == 0) {
            JOptionPane.showMessageDialog(this,"沒有選定任何項目!","錯誤",JOptionPane.ERROR_MESSAGE);
            return;
        }
        MealData data = ValueSearch.search(meals, p -> p.getAmount() <= 0);
        if (data != null) {
            JOptionPane.showMessageDialog(this,"點餐的數量出現非數字字元","錯誤",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this,new SubmitPanel(meals),"帳單明細",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            Student student = Student.getStudent(Integer.parseInt(nid.getText()), Integer.parseInt(sid.getText()));
            for (MealData md : meals) {
                student.Order(new OrderedItem(md.getRestaurant(),md.asMealOrder(),md.getTime()));
            }
            JOptionPane.showMessageDialog(this,"已收到您的訂單! 如後續要確認請至學生查詢\n記得繳交表定金額("
                    +meals.stream().mapToInt(MealData::getPrice).sum()+"$)給相關訂餐同學");
            loadOrderPanel();
            sid.setText("");
            nid.setText("");
        }
    }

    private void showRestaurant(ActionEvent e) {
        if (SELECTED == null) return;
        if (!SELECTED.hasRestaurant()) return;
        JOptionPane.showMessageDialog(this,new ShowRestaurant(SELECTED.getForm()),"檢視餐廳",JOptionPane.PLAIN_MESSAGE);
        loadNest();
        loadButton();
        loadOrderPanel();
    }

    private void ViewData(ActionEvent e) {
        if (SELECTED == null) return;
        if (!SELECTED.hasRestaurant()) return;
        JOptionPane.showMessageDialog(this,new ShowData(SELECTED.getForm()),"檢視訂單",JOptionPane.PLAIN_MESSAGE);
    }

    private void query(ActionEvent e) {
        if (nid_s.getText().isEmpty() || !nid_s.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this,"請輸入座號!","錯誤",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (sid_s.getText().isEmpty() || !sid_s.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this,"請輸入學號!","錯誤",JOptionPane.ERROR_MESSAGE);
            return;
        }
        Student s = Student.getStudent(Integer.parseInt(nid_s.getText()),Integer.parseInt(sid_s.getText()));
        ArrayList<MealData> md = new ArrayList<>();
        for (OrderedItem item : s.ORDERED) {
            if (!item.timeStamp.hasPast()) {
                MealData d = new MealData(item.ordered.meal, item.ordered.amount, item.timeStamp);
                d.setRestaurant(item.restaurant);
                md.add(d);
            }
        }
        LISTP.removeAll();
        LISTP.add(new SubmitPanel(md));
        LISTP.updateUI();
    }

    private void resize(KeyEvent e) {
        if (e != null && e.getKeyCode() != KeyEvent.VK_ENTER) return;
        double scale = e == null ? getScale() : Double.parseDouble(size_display.getText());
        tabbedPane1.setSize((int) (DEFAULT_WIDTH * scale), (int) (DEFAULT_HEIGHT * scale));
        setVisible(false);
        setVisible(true);
    }

    private void resize(MouseEvent e) {
        resize(((KeyEvent) null));
    }

    private void slider1StateChanged(ChangeEvent e) {
        size_display.setText(Double.toString(Math.round(getScale() * 10) / 10D));
    }

    private double getScale() {
        return size.getValue() / 50.0;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        orderScroll = new JScrollPane();
        OrderPanel = new JPanel();
        des_formInfo4 = new JTextField();
        des_formInfo2 = new JTextField();
        nid = new JTextField();
        des_formInfo3 = new JTextField();
        sid = new JTextField();
        button1 = new JButton();
        scrollPane2 = new JScrollPane();
        forms = new JPanel();
        querySingle = new JScrollPane();
        panel1 = new JPanel();
        des_formInfo5 = new JTextField();
        des_formInfo6 = new JTextField();
        nid_s = new JTextField();
        des_formInfo7 = new JTextField();
        sid_s = new JTextField();
        button2 = new JButton();
        LISTP = new JPanel();
        queryAll = new JScrollPane();
        AllS = new JPanel();
        panel2 = new JPanel();
        timeStampPicker1 = new TimeStampPicker();
        allQueryResult = new JPanel();
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
        panel3 = new JPanel();
        des_formInfo8 = new JTextField();
        size = new JSlider();
        size_display = new JTextField();

        //======== this ========
        setTitle("S206 \u9ede\u9910\u7cfb\u7d71");
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

            //======== orderScroll ========
            {

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

                    //---- button1 ----
                    button1.setIcon(new ImageIcon(getClass().getResource("/submit.png")));
                    button1.setOpaque(false);
                    button1.setBorderPainted(false);
                    button1.setFocusPainted(false);
                    button1.setContentAreaFilled(false);
                    button1.setToolTipText("\u9001\u51fa\u8a02\u55ae");
                    button1.addActionListener(e -> submit(e));
                    OrderPanel.add(button1);
                    button1.setBounds(1070, 60, 82, 30);

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
                orderScroll.setViewportView(OrderPanel);
            }
            tabbedPane1.addTab("\u9ede\u9910", orderScroll);

            //======== querySingle ========
            {

                //======== panel1 ========
                {
                    panel1.setLayout(null);

                    //---- des_formInfo5 ----
                    des_formInfo5.setText("\u5fb7\u5149\u4e2d\u5b78 S206 \u67e5\u8a62\u7cfb\u7d71");
                    des_formInfo5.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                    des_formInfo5.setEditable(false);
                    des_formInfo5.setFocusable(false);
                    des_formInfo5.setBorder(null);
                    des_formInfo5.setAutoscrolls(false);
                    des_formInfo5.setHorizontalAlignment(SwingConstants.CENTER);
                    des_formInfo5.setPreferredSize(new Dimension(230, 20));
                    panel1.add(des_formInfo5);
                    des_formInfo5.setBounds(0, 0, 1168, 50);

                    //---- des_formInfo6 ----
                    des_formInfo6.setText("\u5ea7\u865f: ");
                    des_formInfo6.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                    des_formInfo6.setEditable(false);
                    des_formInfo6.setFocusable(false);
                    des_formInfo6.setBorder(null);
                    des_formInfo6.setAutoscrolls(false);
                    panel1.add(des_formInfo6);
                    des_formInfo6.setBounds(415, 60, 65, 30);

                    //---- nid_s ----
                    nid_s.setPreferredSize(new Dimension(100, 30));
                    nid_s.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
                    panel1.add(nid_s);
                    nid_s.setBounds(485, 60, 100, 30);

                    //---- des_formInfo7 ----
                    des_formInfo7.setText("\u5b78\u865f: ");
                    des_formInfo7.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                    des_formInfo7.setEditable(false);
                    des_formInfo7.setFocusable(false);
                    des_formInfo7.setBorder(null);
                    des_formInfo7.setAutoscrolls(false);
                    panel1.add(des_formInfo7);
                    des_formInfo7.setBounds(605, 60, 60, 30);

                    //---- sid_s ----
                    sid_s.setPreferredSize(new Dimension(100, 30));
                    sid_s.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
                    panel1.add(sid_s);
                    sid_s.setBounds(670, 60, 100, 30);

                    //---- button2 ----
                    button2.setIcon(new ImageIcon(getClass().getResource("/submit.png")));
                    button2.setOpaque(false);
                    button2.setBorderPainted(false);
                    button2.setFocusPainted(false);
                    button2.setContentAreaFilled(false);
                    button2.setToolTipText("\u9001\u51fa\u67e5\u8a62");
                    button2.addActionListener(e -> query(e));
                    panel1.add(button2);
                    button2.setBounds(965, 60, 82, 30);

                    //======== LISTP ========
                    {
                        LISTP.setLayout(new FlowLayout());
                    }
                    panel1.add(LISTP);
                    LISTP.setBounds(0, 100, 1165, 610);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel1.getComponentCount(); i++) {
                            Rectangle bounds = panel1.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel1.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel1.setMinimumSize(preferredSize);
                        panel1.setPreferredSize(preferredSize);
                    }
                }
                querySingle.setViewportView(panel1);
            }
            tabbedPane1.addTab("\u500b\u4eba\u67e5\u8a62", querySingle);

            //======== queryAll ========
            {

                //======== AllS ========
                {
                    AllS.setLayout(new BoxLayout(AllS, BoxLayout.Y_AXIS));

                    //======== panel2 ========
                    {
                        panel2.setPreferredSize(new Dimension(695, 50));
                        panel2.setLayout(new FlowLayout());
                        panel2.add(timeStampPicker1);
                    }
                    AllS.add(panel2);

                    //======== allQueryResult ========
                    {
                        allQueryResult.setLayout(new FlowLayout());
                    }
                    AllS.add(allQueryResult);
                }
                queryAll.setViewportView(AllS);
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
                    nextPage.setToolTipText("\u5f8c\u4e00\u9801");
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
                    lastPage.setToolTipText("\u4e0a\u4e00\u9801");
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
                    Today.setToolTipText("\u8df3\u8f49\u81f3\u4eca\u65e5");
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
                        edit.setToolTipText("\u7de8\u8f2f\u9910\u5ef3");
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
                        add.setToolTipText("\u65b0\u589e\u9910\u5ef3");
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
                        remove.setToolTipText("\u79fb\u9664\u9910\u5ef3");
                        remove.addActionListener(e -> remove(e));
                        restaurantPanel.add(remove);
                        remove.setBounds(1090, 60, 30, 30);

                        //---- refresh ----
                        refresh.setForeground(Color.red);
                        refresh.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
                        refresh.setBorderPainted(false);
                        refresh.setContentAreaFilled(false);
                        refresh.setFocusPainted(false);
                        refresh.setToolTipText("\u91cd\u65b0\u8f09\u5165");
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
                    restaurantList.setToolTipText("\u6aa2\u8996\u4eca\u5929\u958b\u653e\u9ede\u9078\u7684\u9910\u5ef3");
                    restaurantList.addActionListener(e -> showRestaurant(e));
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
                    getData.setToolTipText("\u6aa2\u8996\u6216\u4fee\u6539\u4eca\u65e5\u5df2\u9001\u51fa\u7684\u9ede\u9910\u8cc7\u6599");
                    getData.addActionListener(e -> ViewData(e));
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
                    status.setToolTipText("\u5207\u63db\u8868\u55ae\u72c0\u614b");
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
                    setRestaurant.setToolTipText("\u8a2d\u5b9a\u4ee5\u4e0b\u6e05\u55ae\u4e2d\u6240\u9078\u7684\u9910\u5ef3\u9032\u5165\u4eca\u65e5\u9910\u5ef3\u5217\u8868");
                    setRestaurant.addActionListener(e -> AddRestaurantToForm(e));
                    settingPanel.add(setRestaurant);
                    setRestaurant.setBounds(259, 320, 166, 30);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < settingPanel.getComponentCount(); i++) {
                            Rectangle bounds = settingPanel.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = settingPanel.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        settingPanel.setMinimumSize(preferredSize);
                        settingPanel.setPreferredSize(preferredSize);
                    }
                }
                settings.setViewportView(settingPanel);
            }
            tabbedPane1.addTab("\u83dc\u55ae\u8a2d\u5b9a", settings);

            //======== panel3 ========
            {
                panel3.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- des_formInfo8 ----
                des_formInfo8.setText("\u8996\u7a97\u5927\u5c0f");
                des_formInfo8.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                des_formInfo8.setEditable(false);
                des_formInfo8.setFocusable(false);
                des_formInfo8.setBorder(null);
                panel3.add(des_formInfo8);

                //---- size ----
                size.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        resize(e);
                    }
                });
                size.addChangeListener(e -> slider1StateChanged(e));
                panel3.add(size);

                //---- size_display ----
                size_display.setText("1.0");
                size_display.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 20));
                size_display.setBorder(null);
                size_display.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        resize(e);
                    }
                });
                panel3.add(size_display);
            }
            tabbedPane1.addTab("\u76f8\u5bb9\u6027", panel3);
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
    private JScrollPane orderScroll;
    private JPanel OrderPanel;
    private JTextField des_formInfo4;
    private JTextField des_formInfo2;
    private JTextField nid;
    private JTextField des_formInfo3;
    private JTextField sid;
    private JButton button1;
    private JScrollPane scrollPane2;
    private JPanel forms;
    private JScrollPane querySingle;
    private JPanel panel1;
    private JTextField des_formInfo5;
    private JTextField des_formInfo6;
    private JTextField nid_s;
    private JTextField des_formInfo7;
    private JTextField sid_s;
    private JButton button2;
    private JPanel LISTP;
    private JScrollPane queryAll;
    private JPanel AllS;
    private JPanel panel2;
    private TimeStampPicker timeStampPicker1;
    private JPanel allQueryResult;
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
    private JPanel panel3;
    private JTextField des_formInfo8;
    private JSlider size;
    private JTextField size_display;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
