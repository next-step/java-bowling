package bowling.model.frame;

import bowling.model.Pins;
import bowling.utility.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class FinalFrame implements Frame {

    private static final FrameNumber NUMBER = FrameNumber.LAST;

    private final FrameState state;
    private final List<Pins> additionHitPinsGroup;

    private FinalFrame(FrameState state, List<Pins> additionHitPinsGroup) {
        Assert.notNull(state, "thrownBall must not be null");
        Assert.notNull(additionHitPinsGroup, "additionHitPinsGroup must not be null");
        this.state = state;
        this.additionHitPinsGroup = new ArrayList<>(additionHitPinsGroup);
    }

    static FinalFrame init() {
        return of(FrameState.init(), Collections.emptyList());
    }

    static FinalFrame of(FrameState state, List<Pins> additionHitPinsGroup) {
        return new FinalFrame(state, additionHitPinsGroup);
    }

    @Override
    public FrameNumber number() {
        return NUMBER;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return !hasRemainCount() && state.isEndState();
    }

    @Override
    public Frame next(Pins countOfHit) {
        if (state.isEndState()) {
            return of(state, addHitPins(countOfHit));
        }
        return of(state.nextState(countOfHit), this.additionHitPinsGroup);
    }

    @Override
    public boolean hasRemainCount() {
        return state.hasRemainCount();
    }

    @Override
    public Frame addScore(Pins countOfHit) {
        return of(state.addScore(countOfHit), additionHitPinsGroup);
    }

    public FrameState state() {
        return state;
    }

    public List<Pins> additionHitPinsGroup() {
        return Collections.unmodifiableList(additionHitPinsGroup);
    }

    private List<Pins> addHitPins(Pins pins) {
        List<Pins> newPins = new ArrayList<>(additionHitPinsGroup);
        newPins.add(pins);
        return newPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, additionHitPinsGroup);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(state, that.state) && Objects.equals(additionHitPinsGroup, that.additionHitPinsGroup);
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "state=" + state +
                ", additionHitPinsGroup=" + additionHitPinsGroup +
                '}';
    }
}
