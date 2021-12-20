package bowling.domain;

import bowling.strategy.PitchNumberStrategy;

import java.util.Objects;

import static bowling.domain.Pins.PINS_MAX_COUNT;

public class Pitch {
    private final Pins pins;
    private final Pins fallDownPins;

    private Pitch(int fallDownPinsCount) {
        this(Pins.create(), Pins.create(fallDownPinsCount));
    }

    private Pitch(int existPinsCount, int fallDownPinsCount) {
        this(Pins.create(existPinsCount), Pins.create(fallDownPinsCount));
    }

    private Pitch(Pins pins, Pins fallDownPins) {
        this.pins = pins;
        this.fallDownPins = fallDownPins;
    }

    public static Pitch init(int pinsCount, int fallDownPinsCount) {
        return new Pitch(pinsCount, fallDownPinsCount);
    }

    public static Pitch first(int fallDownPinsCount) {
        return new Pitch(fallDownPinsCount);
    }

    public Pitch next(PitchNumberStrategy numberStrategy) {
        if (pins.isEmpty()) {
            return new Pitch(numberStrategy.generate(PINS_MAX_COUNT));
        }
        return new Pitch(pinsSize(), numberStrategy.generate(pinsSize()));
    }

    public Pins run() {
        pins.fallDown(fallDownPins);
        return fallDownPins;
    }

    public boolean isStrike() {
        return fallDownPins.isStrike();
    }

    public boolean isStrike(Pitch pitch) {
        return fallDownPins.isStrike(pitch.fallDownPins);
    }

    public boolean isSpare(Pitch pitch) {
        return this.fallDownPins.isSpare(pitch.fallDownPins);
    }

    public boolean isGutter() {
        return this.fallDownPins.isGutter();
    }

    public int pinsSize() {
        return pins.size();
    }

    public int fallDownPinsSize() {
        return fallDownPins.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return Objects.equals(pins, pitch.pins) && Objects.equals(fallDownPins, pitch.fallDownPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, fallDownPins);
    }
}
