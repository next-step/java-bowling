package bowling.domain;

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

    @Override
    public Frame getNextFrame() {
        if (getScoring() == NONE) {
            return this;
        }
        if (nextFrame == null) {
            nextFrame = getFrame();
        }
        return nextFrame;
    }

    @Override
    public Score getScore() {
        Integer first = getFallingPinOfIndex(this, 0);
        Integer second = getFallingPinOfIndex(this, 1);
        Integer third = null;
        if (getScoring() == STRIKE) {
            second = getFallingPinOfIndex(getNextFrame(), 0);
            third = getFallingPinOfIndex(getNextFrame(), 1);
        }
        if (getScoring() == SPARE) {
            third = getFallingPinOfIndex(getNextFrame(), 0);
        }
        return new Score(first, second, third);
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

    static Integer getFallingPinOfIndex(Frame frame, int index) {
        return frame.getFallingPins(index);
    }
}
