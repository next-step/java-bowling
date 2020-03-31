package bowling.domain.frame;

import bowling.domain.state.*;

import java.util.Objects;

public class Frame {

    private final int frameNumber;
    private State state;

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.state = new Ready();
    }

    public State bowl(int pins) {
        this.state = state.bowl(pins);
        return state;
    }

    public State getState() {
        return state;
    }

    public void renewScore(Score score) {
        state.renewScore(score);
    }

    public Score getScoreByState() {
        return state.getScore();
    }

    public Score calculateByBeforeScore(Score score) {
        return state.calculateByBeforeScore(score);
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    public boolean isCalculation() {
        if (Objects.isNull(state.getScore())) {
            return false;
        }
        return state.getScore().isCalculation();
    }

    public boolean isSpare() {
        return state instanceof Spare;
    }

    public boolean isStrike() {
        return state instanceof Strike;
    }

    public boolean isMiss() {
        return state instanceof Miss;
    }

    public boolean isBonus() {
        return state instanceof Bonus;
    }

    public boolean isFinalFrame() {
        return getFrameNumber() >= 10;
    }

    public int getScore() {
        return state.getScore().getScore();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public String display() {
        return state.display();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return frameNumber == frame.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
