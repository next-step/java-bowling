package bowling.domain;

import bowling.exception.PinOutOfSizeException;

import java.util.Objects;

public class Frame {

    public enum State { STRIKE, SPARE, MISS;}
    private int tryCount;
    private int pins;
    private State status;

    private Frame(int tryCount, int pins) {
        this.tryCount = tryCount;
        this.pins = pins;
    }

    public static Frame init() {
        return new Frame(0, 0);
    }

    public static Frame valueOf(int tryCount, int pins) {
        return new Frame(tryCount, pins);
    }

    public void bowl(int pin) {
        this.pins += pin;
        if (this.pins > 10) {
            throw new PinOutOfSizeException("10개 이상의 핀을 쓰러뜨릴 수 없습니다.");
        }
        if (tryCount == 0 && this.pins == 10) {
            status = State.STRIKE;
            tryCount++;
        } else if (tryCount == 1 && this.pins == 10) {
            status = State.SPARE;
        } else if (tryCount == 1 && this.pins == 0) {
            status = State.MISS;
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
        return String.format("%d", pins);
    }
}
