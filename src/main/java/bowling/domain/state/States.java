package bowling.domain.state;

import bowling.domain.state.None;
import bowling.domain.state.State;

import java.util.LinkedList;

/**
 * Created : 2020-12-21 오전 11:22
 * Developer : Seo
 */
public class States {
    private final LinkedList<State> states;

    public States() {
        this.states = new LinkedList<>();
    }

    public void add(State state) {
        this.states.add(state);
    }

    public State getState(int i) {
        if (states.isEmpty()) {
            return new None();
        }
        return this.states.get(i);
    }

    public State getLast() {
        return this.states.getLast();
    }

    public void set(int frameNo, State state) {
        this.states.set(frameNo, state);
    }

    public int size() {
        return states.size();
    }
}
