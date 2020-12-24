package bowling.domain.state;

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

    public State getState(int userIndex) {
        if (states.isEmpty()) {
            return new None();
        }
        if (states.size() <= userIndex) {
            return new None();
        }
        return this.states.get(userIndex);
    }

    public State getLast() {
        return this.states.getLast();
    }

    public void set(int frameNo, State state) {
        this.states.set(frameNo, state);
    }

    public int size() {
        return this.states.size();
    }
}
