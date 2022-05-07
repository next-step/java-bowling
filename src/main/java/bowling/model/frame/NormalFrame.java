package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.finalization.FinalFrame;
import bowling.model.frame.state.NotThrown;
import bowling.utility.Assert;

import java.util.Objects;

public final class NormalFrame implements Frame {

    private final FrameNumber number;
    private final BallState state;

    private NormalFrame(FrameNumber number, BallState state) {
        Assert.notNull(number, "number must not be null");
        Assert.notNull(state, "state must not be null");
        validateNumber(number);
        this.number = number;
        this.state = state;
    }

    static NormalFrame init(FrameNumber number) {
        return of(number, NotThrown.instance());
    }

    static NormalFrame of(FrameNumber number, BallState state) {
        return new NormalFrame(number, state);
    }

    public Frame next(Pins countOfHit) {
        if (this.state.isEnd()) {
            return nextFrame(countOfHit);
        }
        return of(this.number, this.state.state(countOfHit));
    }

    @Override
    public boolean isEnd() {
        return this.state.isEnd();
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public FrameNumber number() {
        return this.number;
    }

    private void validateNumber(FrameNumber number) {
        if (number.isLast()) {
            throw new IllegalArgumentException(String.format("number(%s) must not be last", number));
        }
    }

    private Frame nextFrame(Pins countOfHit) {
        FrameNumber nextNumber = this.number.increase();
        if (nextNumber.isLast()) {
            return FinalFrame.init().next(countOfHit);
        }
        return init(nextNumber).next(countOfHit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
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
        return Objects.equals(number, that.number);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "number=" + number +
                ", state=" + state +
                '}';
    }
}
