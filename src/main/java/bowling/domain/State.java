package bowling.domain;

import java.util.Arrays;

public enum State {
    GUTTER("-", 0, false),
    ONE("1", 1, false),
    TWO("2", 2, false),
    THREE("3", 3, false),
    FOUR("4", 4, false),
    FIVE("5", 5, false),
    SIX("6", 6, false),
    SEVEN("7", 7, false),
    EIGHT("8", 8, false),
    NINE("9", 9, false),
    STRIKE("X", 10, false),
    SPARE("/", 10, true);

    private final String value;
    private final int fallenPins;
    private final boolean isSpare;

    State(String value, int fallenPins, boolean isSpare) {
        this.value = value;
        this.fallenPins = fallenPins;
        this.isSpare = isSpare;
    }

    public static State valueOf(int fallenPins, boolean isSpare) {
        return Arrays.stream(State.values())
                .filter(value -> value.fallenPins == fallenPins && value.isSpare == isSpare)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 값이 아닙니다."));
    }
}
