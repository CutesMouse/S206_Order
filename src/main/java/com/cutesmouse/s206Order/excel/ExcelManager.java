package com.cutesmouse.s206Order.excel;

import com.cutesmouse.s206Order.restaurant.Restaurant;
import com.cutesmouse.s206Order.student.OrderedItem;
import com.cutesmouse.s206Order.time.TimeStamp;
import com.cutesmouse.s206Order.utils.DisplayText;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelManager {
    public static void output(ArrayList<OrderedItem> orders, File f) throws WriteException {
        //xls file
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DataWrite writer = new DataWrite();
        TABLE = new HashMap<>();
        writer.createExcel(f.getPath());
        writer.createSheet("訂餐資料",0);
        writer.setValueIntoCell(0,0,"時間截記");
        writer.setValueIntoCell(0,1,"座號");
        orders.sort((o1, o2) -> {
            if (o1.timeStamp.toDate().getTime() > o2.timeStamp.toDate().getTime()) {
                return 0;
            } else if (o1.timeStamp.toDate().getTime() == o2.timeStamp.toDate().getTime()) {
                return Comparator.comparing(t -> ((OrderedItem) t).restaurant.name).compare(o1,o2);
            }
            return 1;
        });
        ArrayList<Integer> used = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderedItem order = orders.get(i);
            int index = getIndex(order.restaurant,order.timeStamp);
            if (used.contains(index)) continue;
            used.add(index);
            writer.setValueIntoCell(0,index,new SimpleDateFormat("MM/dd ").format(order.timeStamp.toDate())+
                    DisplayText.DAYOFWEEK(order.timeStamp.day) +" "+order.restaurant.name);
        }
        //2019/4/18 下午 12:48:36
        for (int y = 0 ; y < orders.size(); y++) {
            writer.setValueIntoCell(0,y,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .format(new Date(orders.get(y).PLACETIME)));
            writer.setValueIntoCell(1,y,orders.get(y).student.nid);
        }
    }
    private static HashMap<Map.Entry<Restaurant,TimeStamp>, Integer> TABLE;
    private static int getIndex(Restaurant r, TimeStamp time) {
        for (Map.Entry<Restaurant,TimeStamp> rest : TABLE.keySet()) {
            if (!rest.getKey().equals(r) || !rest.getValue().equals(time)) continue;
            return TABLE.get(rest);
        }
        AbstractMap.SimpleEntry<Restaurant, TimeStamp> key = new AbstractMap.SimpleEntry<>(r, time);
        if (TABLE.size() == 0) {
            TABLE.put(key,2);
            return 2;
        }
        int index = TABLE.values().stream().mapToInt(Integer::intValue).max().getAsInt() + 1;
        TABLE.put(key, index);
        return index;
    }
}
