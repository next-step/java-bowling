package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final Round round;
    private State state;
    private Frame next;

    private NormalFrame(Round round, State state) {
        this.round = round;
        this.state = state;
    }

    public static NormalFrame readyFrame(Round round) {
        return of(round, Ready.getInstance());
    }

    public static NormalFrame of(Round round, State state) {
        return new NormalFrame(round, state);
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public boolean isEqualsRound(Frame frame) {
        return round.equals(frame.round());
    }

    @Override
    public Round round() {
        return this.round;
    }

    @Override
    public Frame bowl(Pin pin) {
        if (state.isFinished()) {
            throw new IllegalArgumentException();
        }
        this.state = state.bowl(pin);

        if (this.state.isFinished()) {
            this.next = FrameFactory.getReadyFrame(this.round.nextRound());
            return this.next;
        }
        return this;
    }

    @Override
    public boolean isGameEnd() {
        return false;
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
