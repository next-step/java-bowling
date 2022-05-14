package bowling.model.frame;

import bowling.model.Pins;
import bowling.utility.Assert;

import java.util.Objects;

public final class NormalFrame implements Frame {

    private final FrameNumber number;
    private final FrameState state;

    private NormalFrame(FrameNumber number, FrameState state) {
        Assert.notNull(number, "number must not be null");
        Assert.isFalse(number.isLast(), String.format("number(%s) must not be last", number));
        Assert.notNull(state, "state must not be null");
        this.number = number;
        this.state = state;
    }

    static NormalFrame init(FrameNumber number) {
        return of(number, FrameState.init());
    }

    static NormalFrame of(FrameNumber number, FrameState state) {
        return new NormalFrame(number, state);
    }

    @Override
    public FrameNumber number() {
        return this.number;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return state.isEndState();
    }

    @Override
    public Frame next(Pins countOfHit) {
        if (isEnd()) {
            return nextFrame()
                    .addScore(countOfHit)
                    .next(countOfHit);
        }
        return of(number, state.nextState(countOfHit));
    }

    @Override
    public boolean hasRemainCount() {
        return state.hasRemainCount();
    }

    @Override
    public Frame addScore(Pins countOfHit) {
        return of(number, state.addScore(countOfHit));
    }

    public FrameState state() {
        return state;
    }

    private Frame nextFrame() {
        FrameNumber nextNumber = this.number.increase();
        if (nextNumber.isLast()) {
            return FinalFrame.init();
        }
        return init(nextNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(number, that.number) && Objects.equals(state, that.state);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "number=" + number +
                ", thrownBall=" + state +
                '}';
    }
}
