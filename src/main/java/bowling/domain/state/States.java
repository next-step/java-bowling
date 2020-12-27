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

    public void add(int playerIndex, State state) {
        this.states.add(playerIndex, state);
    }

    public State getState(int playerIndex) {
        if (states.isEmpty()) {
            return new None();
        }
        if (states.size() <= playerIndex) {
            return new None();
        }
        return this.states.get(playerIndex);
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
