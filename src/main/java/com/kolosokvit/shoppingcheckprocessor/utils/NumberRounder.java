package com.kolosokvit.shoppingcheckprocessor.utils;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberRounder {
    private NumberRounder() {
    }

    public static double roundDouble(double d) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        return bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
