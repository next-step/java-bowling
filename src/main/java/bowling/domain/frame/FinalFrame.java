package bowling.domain.frame;

import static bowling.domain.frame.NormalFrame.FINAL_ROUND;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {

    private final int round;
    private State state;
    private List<State> bonus;

    private FinalFrame(int round, State state, List<State> bonus) {
        this.round = round;
        this.state = state;
        this.bonus = bonus;
    }

    public static Frame from(int round, State state) {
        return new FinalFrame(round, state, new ArrayList<>());
    }

    public static Frame createFinalFrame() {
        return new FinalFrame(FINAL_ROUND, new Ready(), new ArrayList<>());
    }

    @Override
    public Frame bowling(Pin pin) {
        return null;
    }

    @Override
    public FrameResult createFrameResult() {
        return null;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return null;
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
        return round == that.round && Objects.equals(state.getClass(), that.state.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, state.getClass());
    }
}
