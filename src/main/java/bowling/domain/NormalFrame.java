package bowling.domain;

import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateReady;

import java.util.*;

public class NormalFrame implements Frame {
    private final FrameNumber frameNumber;

    private FrameState currentState = new FrameStateReady();
    private Frame next;

    public NormalFrame(Pinfall firstPinfall, Pinfall secondPinfall) {
        this(firstPinfall);
        roll(secondPinfall);
    }

    public NormalFrame(FrameNumber frameNumber, Pinfall firstPinfall) {
        this(frameNumber);
        roll(firstPinfall);
    }

    public NormalFrame(Pinfall pinfall) {
        this();
        roll(pinfall);
    }

    public NormalFrame() {
        this(new FrameNumber(1));
    }

    public NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    @Override
    public Frame roll(Pinfall pinfall) {
        return roll(pinfall, new NormalFrameFactory());
    }

    @Override
    public boolean isDone() {
        return !currentState.isRollable();
    }

    @Override
    public FrameNumber frameNumber() {
        return frameNumber;
    }

    @Override
    public PointSymbols pointSymbols() {
        return currentState.pointSymbols();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }

    @Override
    public Frame roll(Pinfall pinfall, FrameFatory frameFatory) {
        currentState = currentState.roll(pinfall);
        if (isDone()) {
            next = frameFatory.frame(frameNumber.increase());
            return next;
        }
        return this;
    }

    @Override
    public Score score() {
        return currentState.score(nextFrameBonusPinfalls(2));
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Frame next() {
        return next;
    }

    private List<Pinfall> nextFrameBonusPinfalls(int bonusPinfallCount) {
        if (!hasNext()) {
            return new ArrayList<>();
        }

        return next.bonusPinfalls(bonusPinfallCount);
    }

    @Override
    public List<Pinfall> bonusPinfalls(int bonusPinfallCount) {
        List<Pinfall> bonusPinfalls = currentState.pinfalls();
        if (bonusPinfalls.size() == 0) {
            return new ArrayList<>();
        }

        if (bonusPinfalls.size() < bonusPinfallCount) {
            List<Pinfall> nextBonus = nextFrameBonusPinfalls(bonusPinfallCount - bonusPinfalls.size());
            bonusPinfalls.addAll(nextBonus);
        }

        return bonusPinfalls;
    }
}
