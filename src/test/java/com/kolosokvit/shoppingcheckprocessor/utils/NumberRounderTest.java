package com.kolosokvit.shoppingcheckprocessor.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberRounderTest {

    @Test
    void roundDoubleDown() {
        double expectedDouble = 5.55;
        Assertions.assertEquals(expectedDouble, NumberRounder.roundDouble(5.553333333));
    }

    @Test
    void roundDoubleUp() {
        double expectedDouble = 5.56;
        Assertions.assertEquals(expectedDouble, NumberRounder.roundDouble(5.556666666));
    }
}