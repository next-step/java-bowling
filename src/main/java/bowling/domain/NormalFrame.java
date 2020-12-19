package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.Scoring.*;
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
    public Scoring getScoring() {
        if (isIncomplete()) {
            return Scoring.NONE;
        }

        Integer first = getFallingPins(ballThrows, 0);
        Integer second = getFallingPins(ballThrows, 1);
        return Scoring.valueOf(first, second);
    }

    @Override
    public FrameStatus getFrameStatus() {
        Integer first = getFallingPins(ballThrows, 0);
        Integer second = getFallingPins(ballThrows, 1);
        Scoring scoring = Scoring.valueOf(first, second);

        if (scoring.equals(STRIKE)) {
            return FrameStatus.strike();
        }

        if (scoring.equals(SPARE)) {
            return FrameStatus.spare(first);
        }

        return FrameStatus.miss(first, second);
    }

    private Integer getFallingPins(List<BallThrow> ballThrows, int index) {
        return getAsOptional(ballThrows, index)
                .map(BallThrow::getFallingPins)
                .orElse(null);
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
        if (number < 9) {
            return new NormalFrame(number + 1);
        }
        return new LastFrame();
    }

    private boolean isIncomplete() {
        return ballThrows.isEmpty() || !ballThrows.get(0).isStrike() && ballThrows.size() < 2;
    }
}
