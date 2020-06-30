package bowling.domain;

import bowling.common.IntegerUtils;

public enum Shot {
    GUTTER("-"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    SPARE("\\"),
    STRIKE("X");

    private final String symbol;

    Shot(String symbol) {
        this.symbol = symbol;
    }

    public static Shot of(boolean isFirst, int pin, int remain) {
        if (remain == IntegerUtils.ZERO) {
            return isFirst ? STRIKE : SPARE;
        }
        if (pin == 0) {
            return GUTTER;
        }
        if (pin == 1) {
            return ONE;
        }
        if (pin == 2) {
            return TWO;
        }
        if (pin == 3) {
            return THREE;
        }
        if (pin == 4) {
            return FOUR;
        }
        if (pin == 5) {
            return FIVE;
        }
        if (pin == 6) {
            return SIX;
        }
        if (pin == 7) {
            return SEVEN;
        }
        if (pin == 8) {
            return EIGHT;
        }
        if (pin == 9) {
            return NINE;
        }
        throw new IllegalArgumentException("Maybe Frame is invalid");
    }

    public String getSymbol() {
        return symbol;
    }
}
