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

    public Pin getLastPin() {
        int size = this.pins.getPins().size();
        if (size == 0) {
            return new Pin(Pin.MIN_PIN);
        }
        return this.pins.getPins().get(size - 1);
    }

    public Pin getBeforePin() {
        int size = this.pins.getPins().size();
        if (size == 0 || size == 1) {
            return new Pin(Pin.MIN_PIN);
        }

        return getLastPreviousPin(size);
    }

    private Pin getLastPreviousPin(int size) {
        return this.pins.getPins().get(size - 2);
    }

    public State getBeforeState() {
        if (this.states.size() == 0 || this.states.size() == 1) {
            return State.READY;
        }

        return getLastPreviousState();
    }

    private State getLastPreviousState() {
        return this.states.get(this.states.size() - 2);
    }

    public Score calculateScore(Score score) {
        for (Pin pin : getPins()) {
            score = score.bowl(pin.getFallenPin());
        }
        return score;
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
