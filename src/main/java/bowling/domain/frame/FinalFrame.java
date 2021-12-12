package bowling.domain.frame;

import bowling.domain.result.FrameResult;
import bowling.domain.state.Miss;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.*;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
    private static final int DEFAULT_LEFT = 3;
    private static final int END_LEFT = 0;
    private static final int MISS_STATE_LEFT = 0;
    private static final int CALCULATE_LEFT_DEFAULT = 1;

    private final LinkedList<State> states;
    private final int left;

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
        State lastState = states.getLast();
        State nextState = lastState.bowl(pin);
        nextStates(nextState);
        nextLeft(nextState);
        return of(nextStates(nextState), nextLeft(nextState));
    }

    private List<State> nextStates(State state) {
        List<State> nextStates = new ArrayList<>(this.states);
        nextStates.add(state);
        if (state.isFinished()) {
            nextStates.add(Ready.getInstance());
        }
        return nextStates;
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
        if (isFinished()) {
            return new FrameResult(finishedViewString());
        }

        return new FrameResult(states.getLast().viewString());
    }

    private String finishedViewString() {
        State bonusState = states.pollLast();
        String basicViewString = states.stream()
                .filter(State::isFinished)
                .map(State::viewString)
                .collect(Collectors.joining("|"));
        if (bonusState instanceof Ready) {
            return basicViewString;
        }
        return String.join("|", basicViewString, bonusState.viewString());
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
