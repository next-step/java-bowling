package bowling.domain;

import java.util.Arrays;

public enum Shot {
    GUTTER("-", 0, 0),
    ONE("1", 1, 0),
    TWO("2", 2, 0),
    THREE("3", 3, 0),
    FOUR("4", 4, 0),
    FIVE("5", 5, 0),
    SIX("6", 6, 0),
    SEVEN("7", 7, 0),
    EIGHT("8", 8, 0),
    NINE("9", 9, 0),
    SPARE("\\", -1, 1),
    STRIKE("X", 10, 2);

    private final String symbol;
    private final int pinCount;
    private final int bonusCount;

    Shot(String symbol, int pinCount, int bonusCount) {
        this.symbol = symbol;
        this.pinCount = pinCount;
        this.bonusCount = bonusCount;
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

    public int getBonusCount() {
        return bonusCount;
    }
}
