package bowlingRefactor.domain;

import bowlingRefactor.domain.state.State;
import bowlingRefactor.domain.state.completeState.Spare;
import bowlingRefactor.domain.state.middelState.Ready;

import java.util.List;

public class Frame {

    private final FrameNumber frameNumber;
    private State state;

    public Frame(int frameNumber) {
        this(frameNumber, new Ready());
    }

    public Frame(int frameNumber, State state) {
        this.frameNumber = FrameNumber.of(frameNumber);
        this.state = state;
    }

    public void hit(int pin) {
        state = state.bowl(pin, frameNumber.isFinalFrame());
    }

    public Score getScore(int totalScore) {
        return state.getScore(totalScore);
    }

    public Score addBonus(Score score) {
        return state.addBonus(score);
    }

    public List<Pin> unmodifiablePins() {
        return state.getPins();
    }

    public boolean isSpare() {
        return state.isSpare();
    }

    public boolean isLeftPin() {
        return !state.isEnd();
    }

    public FrameNumber getFrameNumber() {
        return this.frameNumber;
    }
}
