package bowling.domain;

import bowling.exception.PinOutOfSizeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Frame {

    public static final int FIRST_BALL = 0;
    public static final int SECOND_BALL = 1;

    private final List<Integer> pins = new ArrayList<>();
    private State status = State.DEFAULT;
    private int tryCount;

    private Frame(final int tryCount, final Integer... pins) {
        this.tryCount = tryCount;
        this.pins.addAll(Arrays.asList(pins));
    }

    public static Frame init() {
        return new Frame(FIRST_BALL, Pin.BOWLING_PIN_MIN_SIZE);
    }

    public static Frame valueOf(final int tryCount, final Integer... pins) {
        return new Frame(tryCount, pins);
    }

    public void bowl(final int pin) {
        pins.add(tryCount, pin);
        if (sum() > Pin.BOWLING_PIN_MAX_SIZE) {
            throw new PinOutOfSizeException("10개 이상의 핀을 쓰러뜨릴 수 없습니다.");
        }
        bowlingState(pin);
    }

    private void bowlingState(final int pin) {
        if (tryCount == FIRST_BALL && pin == Pin.BOWLING_PIN_MAX_SIZE) {
            status = State.STRIKE;
            tryCount++;
        } else if (tryCount == SECOND_BALL && sum() == Pin.BOWLING_PIN_MAX_SIZE) {
            status = State.SPARE;
        } else if (tryCount == SECOND_BALL && (sum() > Pin.BOWLING_PIN_MIN_SIZE && sum() < Pin.BOWLING_PIN_MAX_SIZE)) {
            status = State.MISS;
        } else if (sum() == 0) {
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

    public int pin(int index) {
        return pins.get(index);
    }

    public int sum() {
        return pins.stream()
                .mapToInt(Integer::intValue).sum();
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
        return String.format("%s, %s, %s", tryCount, pins, status);
    }
}
