package bowling.domain.frame;

import bowling.domain.frame.state.FinalFrameStates;
import bowling.domain.frame.state.State;
import bowling.exception.BowlingException;

import java.util.Objects;

public class FinalFrame implements Frame {

    private static final String LAST_FRAME = "10번 이후의 프레임은 생성 불가";

    private FinalFrameStates states;

    public FinalFrame() {
        this.states = FinalFrameStates.of();
    }

    public FinalFrame(FinalFrameStates states) {
        this.states = states;
    }

    @Override
    public Frame bowl(int pinCount) {
        if (isFinish()) {
            throw new BowlingException(State.CANT_THROW_BALL);
        }

        states = states.bowl(pinCount);
        return this;
    }

    @Override
    public boolean isFinish() {
        return states.isFinish();
    }

    @Override
    public Frame createNext() {
        throw new BowlingException(LAST_FRAME);
    }

    @Override
    public Frame getNext() {
        return this;
    }

    @Override
    public State getState() {
        return states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states);
    }
}
