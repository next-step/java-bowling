package bowling.domain;

import bowling.domain.exception.PinsCountException;

import java.util.Objects;
import java.util.stream.IntStream;

public class Pins {
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;
    private static final Pins[] CACHE = IntStream.rangeClosed(MIN_PINS, MAX_PINS)
            .mapToObj(Pins::new)
            .toArray(Pins[]::new);

    private final int pins;

    private Pins(int pitch) {
        this.pins = pitch;
    }

    private static void validatePitch(int pitch) {
        if (pitch < MIN_PINS || pitch > MAX_PINS) {
            throw new PinsCountException();
        }
    }

    public static Pins ofFirstPitch(int firstPitch) {
        validatePitch(firstPitch);
        return CACHE[firstPitch];
    }

    public Pins ofSecondPitch(int secondPitch) {
        validatePitch(this.pins + secondPitch);
        validatePitch(secondPitch);
        return CACHE[secondPitch];
    }

    public int pins() {
        return this.pins;
    }

    public boolean isStrike() {
        return pins == MAX_PINS;
    }

    public boolean isSpare(Pins secondPins) {
        if (pins() + secondPins.pins() == MAX_PINS) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pins)) return false;
        Pins pins = (Pins) o;
        return pins() == pins.pins();
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
