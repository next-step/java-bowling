package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.exception.NotBowlingStateException;
import java.util.Objects;

public class NormalFrame implements Frame {

    private final Round round;
    private final State state;

    private NormalFrame(Round round) {
        this(round, new Ready());
    }

    private NormalFrame(Round round, State state) {
        this.state = state;
        this.round = round;
    }

    public static NormalFrame first() {
        return new NormalFrame(Round.first());
    }

    @Override
    public NormalFrame bowl(Pins pins) {
        validFinished();
        return new NormalFrame(round, state.bowl(pins));
    }

    @Override
    public Frame nextFrame() {
        if (round.isNextFinishRound()) {
            return new FinalFrame();
        }
        return new NormalFrame(round.next());
    }

    @Override
    public Round round() {
        return round;
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public String mark() {
        return state.mark();
    }

    private void validFinished() {
        if (isFinished()) {
            throw new NotBowlingStateException();
        }
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
        return Objects.equals(round, that.round) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, state);
    }

}
