package bowling.pin.domain;

import bowling.state.domain.State;

import java.util.Objects;

public class Pins {

    public static final int initPins = 10;
    public static final int completedPins = 0;

    private int remainingPins;
    private static boolean cleared = false;
    private State state;

    private Pins(int point) {
        this.remainingPins = initPins - point;
    }

    private Pins(State state, int point) {
        this.remainingPins = initPins - point;
        this.state = state;
    }

    public static Pins toStrike(int point) {
        return new Pins(State.STRIKE, point);
    }

    public static Pins toSpare(int point) {
        return new Pins(State.SPARE, point);
    }

    public static Pins toMiss(int point) {
        return new Pins(State.MISS, point);
    }

    public static Pins toGutter(int point) {
        return new Pins(State.GUTTER, point);
    }

    public static Pins pitchResult(int point, int pitchNumber) {
        int remainingPins = initPins - point;
        if (pitchNumber == 1 && remainingPins == 0) {
            return Pins.toStrike(point);
        }

        if (pitchNumber == 2 && remainingPins == 0) {
            return Pins.toSpare(point);
        }

        if (pitchNumber == 1 && remainingPins == initPins) {
            return Pins.toGutter(point);
        }

        if (pitchNumber == 2 && remainingPins == point) {
            return Pins.toGutter(point);
        }

        return Pins.toMiss(point);
    }

    public int getRemainingPins() {
        return remainingPins;
    }

    public boolean isCleared() {
        return cleared;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return remainingPins == pins.remainingPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remainingPins);
    }
}
