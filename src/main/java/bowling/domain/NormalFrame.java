package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bowling.util.Lists.getAsOptional;

public class NormalFrame implements Frame {
    private final List<BallThrow> ballThrows = new ArrayList<>();
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

        Integer first = getAsOptional(ballThrows, 0).map(BallThrow::getFallingPins)
                .orElse(null);
        Integer second = getAsOptional(ballThrows, 1).map(BallThrow::getFallingPins)
                .orElse(null);
        return Scoring.valueOf(first, second);
    }

    public int sumOfFallingPins() {
        return ballThrows.stream().mapToInt(BallThrow::getFallingPins).sum();
    }

    public int getNumber() {
        return number;
    }

    private void assignBallThrow(int fallingPins) {
        if (ballThrows.isEmpty()) {
            ballThrows.add(new BallThrow(fallingPins));
            return;
        }
        ballThrows.add(ballThrows.get(0).throwSecond(fallingPins));
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
        return ballThrows.isEmpty() || !ballThrows.get(0).isStrike() && ballThrows.size() < 2;
    }
}
