package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bowling.domain.BallThrow.MAX_PINS;
import static bowling.domain.Scoring.STRIKE;

class LastFrame implements Frame {

    private final List<BallThrow> ballThrows = new ArrayList<>();

    @Override
    public Frame throwBall(int fallingPins) {
        if (ballThrows.isEmpty()) {
            ballThrows.add(new BallThrow(fallingPins, true));
            return this;
        }

        if (ballThrows.size() == 1) {
            ballThrows.add(getLastThrow().throwSecond(fallingPins));
            return this;
        }

        ballThrows.add(getLastThrow().throwThird(fallingPins, getFirstBallThrow()));
        return this;
    }

    @Override
    public Optional<Scoring> getScoring() {
        if (ballThrows.isEmpty()) {
            return Optional.empty();
        }

        if (getLastThrow().isStrike()) {
            return STRIKE.asOptional();
        }

        if (isNotFirstThrowStrike() && ballThrows.size() < 2) {
            return Optional.empty();
        }

        return Scoring.nonStrikeValueOf(sumOfFallingPins())
                .asOptional();
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public int sumOfFallingPins() {
        int skipBallThrows = 0;
        if (!isNotFirstThrowStrike() && ballThrows.size() == 3) {
            skipBallThrows = 1;
        }

        return ballThrows.stream()
                .skip(skipBallThrows)
                .mapToInt(BallThrow::getFallingPins)
                .sum();
    }

    public boolean isFinish() {
        if (ballThrows.isEmpty()) {
            return false;
        }
        if (isNotFirstThrowStrike() && sumOfFallingPins() < MAX_PINS) {
            return ballThrows.size() == 2;
        }
        return ballThrows.size() == 3;
    }

    private BallThrow getLastThrow() {
        return ballThrows.get(ballThrows.size() - 1);
    }

    private boolean isNotFirstThrowStrike() {
        return !getFirstBallThrow().isStrike();
    }

    private BallThrow getFirstBallThrow() {
        return ballThrows.get(0);
    }
}
