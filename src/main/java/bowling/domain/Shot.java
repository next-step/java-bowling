package bowling.domain;

import java.util.Arrays;

public enum Shot {
    GUTTER("-", 0),
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    SPARE("\\", -1),
    STRIKE("X", 10);

    private final String symbol;
    private final int pinCount;

    Shot(String symbol, int pinCount) {
        this.symbol = symbol;
        this.pinCount = pinCount;
    }

    public static Shot of(boolean isFirst, Pin fallenPin, Pin remainPin) {
        if (remainPin.isZeroPin()) {
            return isFirst ? STRIKE : SPARE;
        }

        return Arrays.stream(values())
                .filter(s -> s.pinCount == fallenPin.getCount())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Maybe Frame is invalid"));
    }

    public String getSymbol() {
        return symbol;
    }
}
