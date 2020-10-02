package com.cutesmouse.s206Order.time;

import com.cutesmouse.s206Order.window.TimeStampPicker;

public interface TimeStampPickerEvent {
    void run(boolean isMultiSelection, TimeStampPicker picker);
}
