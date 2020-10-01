package com.cutesmouse.s206Order.form;

import com.cutesmouse.s206Order.formWindow.FormPanel;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FormManager {
    public static ArrayList<FormInfo> getEnabled() {
        ArrayList<FormInfo> result = new ArrayList<>();
        FormInfo.FORM_INFOS.forEach((t,i) -> {
            if (i.isToggled()) result.add(i);
        });
        return result;
    }
    public static ArrayList<FormPanel> getPanels() {
        return getEnabled().stream().map(FormPanel::new).collect(Collectors.toCollection(ArrayList::new));
    }
}
