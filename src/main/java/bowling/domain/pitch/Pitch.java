package bowling.domain.pitch;

import bowling.domain.pitch.exception.OverPinsException;

import java.util.Objects;
import java.util.Optional;

public class Pitch {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private final int pins;

    private Pitch(int pins) {
        this.pins = pins;
    }

    public int pins() {
        return this.pins;
    }

    public static Pitch of(int pins) {
        isOverPins(MIN_PINS, pins);

        return new Pitch(pins);
    }

    public Pitch next(int pins) {
        int totalPins = Math.addExact(this.pins, pins);
        isOverPins(this.pins, totalPins);

        return of(pins);
    }

    private static void isOverPins(int currentPins, int nextPins) {
        int remainingPins = Math.subtractExact(MAX_PINS, currentPins);

        Optional.of(nextPins)
                .filter(pins -> MIN_PINS <= pins)
                .filter(pins -> pins <= MAX_PINS)
                .orElseThrow(() -> new OverPinsException(remainingPins));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return pins == pitch.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
