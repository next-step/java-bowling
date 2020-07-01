package bowling.domain.state;

import bowling.domain.state.running.Ready;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FinalFrameStates {

    private final Stack<State> states;

    private FinalFrameStates() {
        this.states = new Stack<>();
        this.states.push(Ready.getInstance());
    }

    public static FinalFrameStates newInstance() {
        return new FinalFrameStates();
    }

    public State getLastState() {
        return this.states.peek();
    }

    public State getFirstState() {
        return this.states.get(0);
    }

    public void updateLastState(final State state) {
        this.states.pop();
        this.states.push(state);
    }

    public void push(final State state) {
        this.states.push(state);
    }

    public int size() {
        return this.states.size();
    }

    public State indexOf(final int index) {
        return this.states.get(index);
    }

    public Stack<State> getStates() {
        return states;
    }

    public List<State> toList() {
        return new ArrayList<>(this.states);
    }
}
