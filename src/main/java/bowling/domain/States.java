package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class States {
    private final List<State> states;
    private final Pins pins;

    public States() {
        this.states = new ArrayList<>();
        this.pins = new Pins();
    }

    public void add(State state, Pin pin) {
        this.states.add(state);
        this.pins.add(pin);
    }

    public State getLastState() {
        if (this.states.size() == 0) {
            return State.READY;
        }
        return this.states.get(this.states.size() - 1);
    }

    public boolean isLastStateStrike() {
        return getLastState().isStrike();
    }

    public int getStatesPinSum() {
        return this.states
                .stream()
                .mapToInt(State::getFallenPins)
                .sum();
    }

    public State getBeforeState() {
        if (this.states.size() == 0 || this.states.size() == 1) {
            return State.READY;
        }

        return this.states.get(this.states.size() - 2);
    }

    public int getSize() {
        return this.states.size();
    }

    public List<State> getStates() {
        return states;
    }

    public List<Pin> getPins() {
        return pins.getPins();
    }
}
