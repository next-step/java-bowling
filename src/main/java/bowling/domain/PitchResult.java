package bowling.domain;

import java.util.Objects;

public class PitchResult {

    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private final int fallenPins;
    private final boolean isSpare;

    private PitchResult(int fallenPins, boolean isSpare) {
        validateFallenPins(fallenPins);
        this.fallenPins = fallenPins;
        this.isSpare = isSpare;
    }

    public static PitchResult of(int fallenPins) {
        return new PitchResult(fallenPins, false);
    }

    public static PitchResult spare(int fallenPins) {
        if (fallenPins == MIN_PINS) {
            throw new IllegalArgumentException();
        }
        return new PitchResult(fallenPins, true);
    }

    public int fallenPins() {
        return fallenPins;
    }

    private void validateFallenPins(int fallenPins) {
        if (fallenPins < MIN_PINS || fallenPins > MAX_PINS) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitchResult that = (PitchResult) o;
        return fallenPins == that.fallenPins && isSpare == that.isSpare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins, isSpare);
    }

}
