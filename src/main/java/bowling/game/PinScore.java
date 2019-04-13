package bowling.game;

import java.util.Arrays;

public enum PinScore {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10);

    private final int numberOfPins;

    PinScore(int numberOfPins) {
        this.numberOfPins = numberOfPins;
    }

    public static PinScore of(int numberOfPins) {
        return Arrays.stream(values())
                .filter(pinScore -> pinScore.numberOfPins == numberOfPins)
                .findFirst()
                .orElseThrow(() -> new IllegalAccessError("Unable number of pins"));
    }

    public int sumPins(PinScore other) {
        return this.numberOfPins + other.numberOfPins;
    }

    public boolean isTen() {
        return (TEN == this);
    }

    @Override
    public String toString() {
        if (ZERO == this) {
            return "-";
        }

        if (TEN == this) {
            return "X";
        }

        return String.valueOf(this.numberOfPins);
    }
}
