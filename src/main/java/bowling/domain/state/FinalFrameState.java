package bowling.domain.state;

import bowling.domain.frame.Pin;

import java.util.*;

public class FinalFrameState implements State {

    private static final int DEFAULT_LEFT = 3;
    private static final int END_LEFT = 0;
    private static final int MISS_STATE_LEFT = 0;
    private static final int CALCULATE_LEFT_DEFAULT = 1;

    private final LinkedList<State> states;
    private final int left;

    private FinalFrameState(List<State> states, int left) {
        this.states = new LinkedList<>(states);
        this.left = left;
    }

    public static FinalFrameState readyState() {
        return of(Arrays.asList(Ready.getInstance()), DEFAULT_LEFT);
    }

    public static FinalFrameState of(List<State> states, int left) {
        return new FinalFrameState(states, left);
    }

    @Override
    public boolean isFinished() {
        return left == END_LEFT;
    }

    @Override
    public State bowl(Pin pin) {
        State lastState = states.getLast();
        return nextState(lastState.bowl(pin));
    }

    private State nextState(State state) {
        ArrayList<State> nextStates = new ArrayList<>(this.states);
        nextStates.add(state);
        if (state instanceof Spare || state instanceof Strike) {
            nextStates.add(Ready.getInstance());
        }
        return of(nextStates, nextLeft(state));
    }


    private int nextLeft(State state) {
        if (state instanceof Miss) {
            return MISS_STATE_LEFT;
        }
        return left - CALCULATE_LEFT_DEFAULT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinalFrameState that = (FinalFrameState) o;
        return Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states);
    }
}
