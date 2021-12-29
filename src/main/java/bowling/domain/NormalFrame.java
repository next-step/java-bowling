package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;
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
    public NormalFrame bowl(Pins pins) {
        validFinished();
        return new NormalFrame(round, state.bowl(pins));
    }

    @Override
    public String mark() {
        return state.mark();
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    private void validFinished() {
        if (isFinished()) {
            throw new RuntimeException("진행할 수 없는 프레임 입니다.");
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
