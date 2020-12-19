package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.BallThrow.MAX_PINS;
import static bowling.domain.Scoring.*;
import static bowling.util.Lists.getAsOptional;
import static java.util.stream.Collectors.toList;

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
    public Scoring getScoring() {
        if (ballThrows.isEmpty()) {
            return NONE;
        }

        if (getLastThrow().isStrike()) {
            return STRIKE;
        }

        if (isNotFirstThrowStrike() && ballThrows.size() == 1) {
            return NONE;
        }

        List<BallThrow> lastTwoThrows = ballThrows.stream()
                .skip(getSkipBallThrows())
                .collect(toList());

        Integer first = getAsOptional(lastTwoThrows, 0).map(BallThrow::getFallingPins).orElse(null);
        Integer second = getAsOptional(lastTwoThrows, 1).map(BallThrow::getFallingPins).orElse(null);

        return Scoring.valueOf(first, second);
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public List<BallThrow> getBallThrows() {
        return null;
    }

    @Override
    public boolean isFinish() {
        if (ballThrows.isEmpty()) {
            return false;
        }
        if (isNotFirstThrowStrike() && sumOfLastTwoThrows() < MAX_PINS) {
            return ballThrows.size() == 2;
        }
        return ballThrows.size() == 3;
    }

    private int sumOfLastTwoThrows() {
        return ballThrows.stream()
                .skip(getSkipBallThrows())
                .mapToInt(BallThrow::getFallingPins)
                .sum();
    }

    private int getSkipBallThrows() {
        if (isFirstThrowStrike()) {
            return 1;
        }
        return 0;
    }

    private BallThrow getLastThrow() {
        return ballThrows.get(ballThrows.size() - 1);
    }

    private boolean isNotFirstThrowStrike() {
        return !isFirstThrowStrike();
    }

    private boolean isFirstThrowStrike() {
        return getFirstBallThrow().isStrike();
    }

    private BallThrow getFirstBallThrow() {
        return ballThrows.get(0);
    }
}
