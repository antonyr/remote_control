package com.indix.utils;

public class MathUtils {
    public static Integer min(Integer...values){
        Integer minimum = -1;
        for (Integer value : values) {
            if (minimum == -1)
                minimum = value;
            if (minimum.compareTo(value) > 0)
                minimum = value;
        }
        return minimum;
    }
}
