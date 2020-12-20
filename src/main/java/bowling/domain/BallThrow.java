package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BallThrow {
    public static final int MAX_PINS = 10;
    public static final int MIN_PINS = 0;

    private final FallingPins fallingPins;
    private final boolean lastFrame;

    public BallThrow(int fallingPins) {
        this(fallingPins, false);
    }

    public BallThrow(int fallingPins, boolean lastFrame) {
        this.fallingPins = FallingPins.valueOf(fallingPins);
        this.lastFrame = lastFrame;
    }

    public BallThrow throwSecond(int secondFallingPins) {
        if (fallingPins.isNotAddable(secondFallingPins) && !lastFrame) {
            throw new IllegalFallingPinsException();
        }
        if (fallingPins.isNotAddable(secondFallingPins) && lastFrame && fallingPins.isUnder(MAX_PINS)) {
            throw new IllegalFallingPinsException();
        }
        if (fallingPins.isStrike() && !lastFrame) {
            throw new IllegalBallThrownException();
        }
        return new BallThrow(secondFallingPins, lastFrame);
    }

    public BallThrow throwThird(int thirdFallingPins, BallThrow firstBallThrow) {
        if (!lastFrame) {
            throw new IllegalBallThrownException();
        }
        if (fallingPins.isNotClear(firstBallThrow.fallingPins)) {
            throw new IllegalBallThrownException();
        }
        return new BallThrow(thirdFallingPins, true);
    }

    public int getFallingPins() {
        return fallingPins.toInt();
    }

    public boolean isStrike() {
        return fallingPins.isStrike();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BallThrow ballThrow = (BallThrow) o;
        return fallingPins.equals(ballThrow.fallingPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallingPins);
    }

    static class FallingPins {
        private static final Map<Integer, FallingPins> CACHE = new HashMap<>();
        public static FallingPins valueOf(int fallingPins) {
            return CACHE.computeIfAbsent(fallingPins, FallingPins::new);
        }

        private final int value;

        private FallingPins(int fallingPins) {
            if (fallingPins > MAX_PINS || fallingPins < MIN_PINS) {
                throw new IllegalFallingPinsException();
            }
            this.value = fallingPins;
        }

        boolean isStrike() {
            return value == MAX_PINS;
        }

        public int toInt() {
            return value;
        }

        public boolean isNotClear(FallingPins fallingPins) {
            return value + fallingPins.value < MAX_PINS;
        }

        public int add(int fallingPins) {
            return value + fallingPins;
        }

        public boolean isUnder(int number) {
            return value < number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            FallingPins that = (FallingPins) o;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        public boolean isNotAddable(int number) {
            return value + number > MAX_PINS;
        }
    }
}
