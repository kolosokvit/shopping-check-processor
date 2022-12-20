package com.kolosokvit.shoppingcheckprocessor.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LineFormatterTest {
    int targetLength = 7;
    String shortLine = "012345";
    String longLine = "0123456789";

    @Test
    void formatShortLine() {
        String expectedLine = "012345 ";
        Assertions.assertEquals(expectedLine, LineFormatter.formatLine(shortLine, targetLength));
    }

    @Test
    void formatLongLine() {
        String expectedLine = "0123456";
        Assertions.assertEquals(expectedLine, LineFormatter.formatLine(longLine, targetLength));
    }
}