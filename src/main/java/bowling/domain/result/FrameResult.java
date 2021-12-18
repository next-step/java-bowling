package bowling.domain.result;

import bowling.domain.frame.Score;
import bowling.domain.state.State;

import java.util.*;

public class FrameResult {
    private final List<State> states;
    private final Score score;

    private FrameResult(List<State> states, Score score) {
        this.states = new ArrayList<>(states);
        this.score = score;
    }

    public static FrameResult ofNormalFrame(State state, Score score) {
        return new FrameResult(Arrays.asList(state), score);
    }

    public static FrameResult ofFinalFrame(List<State> states, Score score) {
        return new FrameResult(states, score);
    }

    public List<State> getStates() {
        return Collections.unmodifiableList(states);
    }

    public Score getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameResult that = (FrameResult) o;
        return Objects.equals(states, that.states) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states, score);
    }
}
