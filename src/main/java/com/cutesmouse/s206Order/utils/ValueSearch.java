package com.cutesmouse.s206Order.utils;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

public class ValueSearch {
    public static <T> T search(ArrayList<T> ts, Predicate<T> isMatch) {
        if (ts.size() == 0) return null;
        return ts.stream().filter(isMatch).findFirst().orElse(null);
    }
}
