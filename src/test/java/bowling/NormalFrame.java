package bowling;

import java.util.Optional;

import static bowling.BallThrow.MAX_PINS;
import static bowling.BallThrow.MIN_PINS;
import static bowling.Scoring.*;

class NormalFrame implements Frame {
    private BallThrow firstThrow;
    private BallThrow secondThrow;
    private final int number;

    public NormalFrame() {
        this(1);
    }

    NormalFrame(int number) {
        this.number = number;
    }

    @Override
    public Frame throwBall(int fallingPins) {
        assignBallThrow(fallingPins);
        return getNextFrame();
    }

    @Override
    public Optional<Scoring> getScoring() {
        if (isIncomplete()) {
            return Optional.empty();
        }
        if (firstThrow.isStrike()) {
            return Optional.of(STRIKE);
        }
        if (sumOfFallingPins() == MAX_PINS) {
            return Optional.of(SPARE);
        }
        if (sumOfFallingPins() == MIN_PINS) {
            return Optional.of(GUTTER);
        }
        return Optional.of(MISS);
    }

    public int sumOfFallingPins() {
        return Optional.ofNullable(firstThrow)
                .map(ballThrow -> ballThrow.add(secondThrow))
                .orElse(0);
    }

    public int getNumber() {
        return number;
    }

    private void assignBallThrow(int fallingPins) {
        if (firstThrow == null) {
            firstThrow = new BallThrow(fallingPins);
            return;
        }
        secondThrow = firstThrow.throwSecond(fallingPins);
    }

    private Frame getNextFrame() {
        return getScoring().map(__ -> {
            if (number < 9) {
                return new NormalFrame(number + 1);
            }
            return new LastFrame();
        }).orElse(this);
    }

    private boolean isIncomplete() {
        return firstThrow == null || !firstThrow.isStrike() && secondThrow == null;
    }
}
