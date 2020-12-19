package bowling.domain;

import bowling.util.Lists;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.Scoring.*;

public class NormalFrame implements Frame {
    private final List<BallThrow> ballThrows = new ArrayList<>();
    private final int number;
    private Frame nextFrame;

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
    public Scoring getScoring() {
        if (isIncomplete()) {
            return Scoring.NONE;
        }

        Integer first = getFallingPins(0);
        Integer second = getFallingPins(1);
        return Scoring.valueOf(first, second);
    }

    @Override
    public List<BallThrow> getBallThrows() {
        return ballThrows;
    }

    @Override
    public Score getScore() {
        Integer first = Lists.getAsOptional(ballThrows, 0).map(BallThrow::getFallingPins).orElse(null);
        Integer second = Lists.getAsOptional(ballThrows, 1).map(BallThrow::getFallingPins).orElse(null);
        Integer third = null;
        if (getScoring() == STRIKE) {
            second = Lists.getAsOptional(getNextFrame().getBallThrows(), 0).map(BallThrow::getFallingPins).orElse(null);
            third = Lists.getAsOptional(getNextFrame().getBallThrows(), 1).map(BallThrow::getFallingPins).orElse(null);
        }
        if (getScoring() == SPARE) {
            third = Lists.getAsOptional(getNextFrame().getBallThrows(), 0).map(BallThrow::getFallingPins).orElse(null);
        }
        return new Score(first, second, third);

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
        if (getScoring() == NONE) {
            return this;
        }
        if (nextFrame == null) {
            nextFrame = getFrame();
        }
        return nextFrame;
    }

    private Frame getFrame() {
        if (number < 9) {
            return new NormalFrame(number + 1);
        }
        return new LastFrame();
    }

    private boolean isIncomplete() {
        return ballThrows.isEmpty() || !ballThrows.get(0).isStrike() && ballThrows.size() < 2;
    }
}
