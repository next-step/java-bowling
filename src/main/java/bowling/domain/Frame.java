package bowling.domain;

import bowling.exception.PinOutOfSizeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Frame {

    public enum State {STRIKE, SPARE, MISS, GUTTER, DEFAULT}

    private int tryCount;
    private final List<Integer> pins;
    private State status = State.DEFAULT;

    private Frame(final int tryCount, final int pins) {
        this.tryCount = tryCount;
        this.pins = new ArrayList<>();
    }

    public static Frame init() {
        return new Frame(0, 0);
    }

    public static Frame valueOf(final int tryCount, final int pins) {
        return new Frame(tryCount, pins);
    }

    public void bowl(final int pin) {
        if (pin > 10) {
            throw new PinOutOfSizeException("10개 이상의 핀을 쓰러뜨릴 수 없습니다.");
        }

        bowlingState(pin);
        pins.add(pin);
    }

    private void bowlingState(int pin) {
        if (tryCount == 0 && pin == 10) {
            status = State.STRIKE;
            tryCount++;
        } else if (tryCount == 1 && pin == 10) {
            status = State.SPARE;
        } else if (tryCount == 1 && (pin > 1 && pin < 10)) {
            status = State.MISS;
        } else if (pin == 0) {
            status = State.GUTTER;
        }
        tryCount++;
    }

    public int tryCount() {
        return tryCount;
    }

    public boolean isNextFrame() {
        return tryCount > 1;
    }

    public State state() {
        return status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Frame)) return false;
        final Frame frame = (Frame) o;
        return pins == frame.pins
                && tryCount == frame.tryCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, tryCount);
    }

    @Override
    public String toString() {
        return String.valueOf(pins);
    }
}
