package bowling.domain;

import bowling.strategy.PitchNumberStrategy;

import java.util.Objects;

public class Pitch {
    private static final int PINS_MIN_COUNT = 0;
    private static final int PINS_MAX_COUNT = 10;

    private final Pins pins;
    private final Pins fallDownPins;

    private Pitch() {
        this(Pins.create(), Pins.create());
    }

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

    public static Pitch init() {
        return new Pitch();
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
        return new Pitch(pins.size(), numberStrategy.generate(pinsSize()));
    }

    public void run() {
        pins.fallDown(fallDownPins);
    }

    public boolean isStrike() {
        return pins.isStrike(fallDownPins);
    }

    public boolean isSecondStrike(Pitch pitch) {
        return pitch.fallDownPinsSize() == PINS_MAX_COUNT && pins.isStrike(fallDownPins);
    }

    public boolean isSpare(Pitch pitch) {
        return this.fallDownPinsSize() + pitch.fallDownPinsSize() == PINS_MAX_COUNT;
    }

    public boolean isGutter() {
        return this.fallDownPinsSize() == PINS_MIN_COUNT;
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
