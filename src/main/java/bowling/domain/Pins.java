package bowling.domain;

import bowling.domain.exception.PinsCountException;

import java.util.Objects;
import java.util.stream.IntStream;

public class Pins {
    public static final int MAX_PINS = 10;
    public static final int MIN_PINS = 0;
    private static final Pins[] CACHE = IntStream.rangeClosed(MIN_PINS, MAX_PINS)
            .mapToObj(Pins::new)
            .toArray(Pins[]::new);

    private final int fallingPins;

    private Pins(int pitch) {
        this.fallingPins = pitch;
    }

    private static void validatePitch(int pitch) {
        if (pitch < MIN_PINS || pitch > MAX_PINS) {
            throw new PinsCountException();
        }
    }

    public static Pins of(int firstPitch) {
        validatePitch(firstPitch);
        return CACHE[firstPitch];
    }

    public int fallingPins() {
        return this.fallingPins;
    }

    public void isSecondPitchable(Pins secondPitch) {
        validatePitch(fallingPins + secondPitch.fallingPins);
    }

    public boolean isStrike() {
        return fallingPins == MAX_PINS;
    }

    public boolean isSpare(Pins secondPins) {
        if (fallingPins() + secondPins.fallingPins() == MAX_PINS) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pins)) return false;
        Pins pins = (Pins) o;
        return fallingPins() == pins.fallingPins();
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallingPins);
    }
}
