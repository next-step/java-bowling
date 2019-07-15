package com.jaeyeonling.bowling.utils;

public final class StringUtils {

    private static final String BLANK = " ";
    private static final int HALF = 2;

    private StringUtils() { }

    public static String alignCenter(final String alignTarget,
                                     final int size) {
        if (alignTarget.length() >= size) {
            return alignTarget;
        }

        final int paddingSize = (size - alignTarget.length()) / HALF;
        final String padding = BLANK.repeat(paddingSize);

        return String.format(alignFormat(size), alignTarget + padding);
    }

    private static String alignFormat(final int size) {
        return String.format("%s%d%s", "%", size, "s");
    }
}
