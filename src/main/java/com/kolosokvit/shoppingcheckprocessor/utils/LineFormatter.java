package com.kolosokvit.shoppingcheckprocessor.utils;

public class LineFormatter {
    private LineFormatter() {
    }

    public static String formatLine(String line, int length) {
        if (line.length() > length) {
            return line.substring(0, length);
        } else {
            StringBuilder stringBuilder = new StringBuilder(line);
            while (stringBuilder.length() < length) {
                stringBuilder.append(" ");
            }
            return stringBuilder.toString();
        }
    }
}