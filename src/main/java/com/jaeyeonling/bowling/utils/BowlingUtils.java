package com.jaeyeonling.bowling.utils;

import com.jaeyeonling.bowling.domain.BowlingSymbol;

public final class BowlingUtils {

    private static final int FORMAT_LENGTH = 7;

    private BowlingUtils() { }

    public static String format(final String target) {
        return StringUtils.alignCenter(target, FORMAT_LENGTH) + BowlingSymbol.DELIMITER;
    }
}
