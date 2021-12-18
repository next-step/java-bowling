package bowling.domain.frame;

import bowling.domain.result.FrameResult;
import bowling.domain.state.Miss;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {
    private static final int DEFAULT_LEFT = 3;
    private static final int END_LEFT = 0;
    private static final int MISS_STATE_LEFT = 0;
    private static final int CALCULATE_LEFT_DEFAULT = 1;
    private LinkedList<State> states;
    private int left;

    private FinalFrame(List<State> states, int left) {
        this.states = new LinkedList<>(states);
        this.left = left;
    }

    public static FinalFrame readyFrame() {
        return of(Arrays.asList(Ready.getInstance()), DEFAULT_LEFT);
    }

    public static FinalFrame of(List<State> states, int left) {
        return new FinalFrame(states, left);
    }

    @Override
    public Frame bowl(Pin pin) {
        if (isFinished()) {
            throw new IllegalArgumentException();
        }
        State lastState = lastState();
        State nextState = lastState.bowl(pin);
        states.add(nextState);
        left = nextLeft(nextState);
        return this;
    }

    private State lastState() {
        State state = states.getLast();
        if (state.isFinished() && states.size() < DEFAULT_LEFT) {
            return Ready.getInstance();
        }
        return states.pollLast();
    }

    private int nextLeft(State state) {
        if (state instanceof Miss) {
            return MISS_STATE_LEFT;
        }
        return left - CALCULATE_LEFT_DEFAULT;
    }

    @Override
    public boolean isGameEnd() {
        return isFinished();
    }

    @Override
    public boolean isFinished() {
        return left == END_LEFT;
    }

    @Override
    public boolean isEqualsRound(Frame frame) {
        return round().equals(frame.round());
    }

    @Override
    public Round round() {
        return Round.LAST;
    }

    @Override
    public FrameResult createResult() {
        return FrameResult.ofFinalFrame(states, score());
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public Score score() {
        if (isFinished()) {
            return createScore();
        }
        return Score.noScore();
    }

    private Score createScore() {
        Score score = states.getFirst().score();
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateAdditionalScore(score);
        }
        return score;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        for (State state : states) {
            score = state.calculateAdditionalScore(score);
            if (score.canCalculateScore()) {
                return score;
            }
        }
        return Score.noScore();
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
        return left == that.left && Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states, left);
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "states=" + states +
                ", left=" + left +
                '}';
    }
}
