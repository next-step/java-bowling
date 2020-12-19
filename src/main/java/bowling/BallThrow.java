package bowling;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

class BallThrow {
    public static final int MAX_PINS = 10;
    public static final int MIN_PINS = 0;
    private final int fallingPins;
    private final boolean lastFrame;

    public BallThrow(int fallingPins) {
        this(fallingPins, false);
    }

    public BallThrow(int fallingPins, boolean lastFrame) {
        if (fallingPins > MAX_PINS || fallingPins < MIN_PINS) {
            throw new IllegalFallingPinsException();
        }
        this.fallingPins = fallingPins;
        this.lastFrame = lastFrame;
    }

    public BallThrow throwSecond(int secondFallingPins) {
        if (this.fallingPins + secondFallingPins > MAX_PINS && !lastFrame) {
            throw new IllegalFallingPinsException();
        }
        if (this.fallingPins + secondFallingPins > MAX_PINS && lastFrame && this.fallingPins < MAX_PINS) {
            throw new IllegalFallingPinsException();
        }
        if (this.fallingPins == MAX_PINS && !lastFrame) {
            throw new IllegalBallThrownException();
        }
        return new BallThrow(secondFallingPins, lastFrame);
    }

    public BallThrow throwThird(int thirdFallingPins, BallThrow firstBallThrow) {
        if (!lastFrame) {
            throw new IllegalBallThrownException();
        }
        if (firstBallThrow.fallingPins != MAX_PINS) {
            throw new IllegalBallThrownException();
        }
        if (this.fallingPins + thirdFallingPins > MAX_PINS && this.fallingPins < MAX_PINS) {
            throw new IllegalFallingPinsException();
        }
        return new BallThrow(thirdFallingPins);
    }

    public int add(BallThrow ballThrows) {
        return fallingPins + Optional.ofNullable(ballThrows)
                .map(ballThrow -> ballThrow.fallingPins)
                .orElse(0);
    }

    public static int sumOfFallingPins(List<BallThrow> ballThrows) {
        return ballThrows.stream()
                .mapToInt(ballThrow -> ballThrow.fallingPins)
                .sum();
    }

    public boolean isStrike() {
        return fallingPins == MAX_PINS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BallThrow ballThrow = (BallThrow) o;
        return fallingPins == ballThrow.fallingPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallingPins);
    }
}
