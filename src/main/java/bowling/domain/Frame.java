package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;

import java.util.List;

public class Frame {
    private FrameNumber frameNumber;
    private State state;

    public Frame(int frameNumber, State state) {
        this.frameNumber = FrameNumber.of(frameNumber);
        this.state = state;
    }

    public Frame(int frameNumber) {
        this(frameNumber, new Ready());
    }

    public void fallDown(int pin) {
        state = state.bowl(pin, frameNumber.isLastFrame());
    }

    public Score getScore(int totalScore) {
        return state.getScore(totalScore);
    }

    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    public Score addBonus(Score score) {
        return state.addBonus(score);
    }

    public List<Pin> unmodifiablePins() {
        return state.getPins();
    }

    public boolean isSpare() {
        return state instanceof Spare;
    }

    public boolean isFallDownAble() {
        return !state.isEnd();
    }
}
