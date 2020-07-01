package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class States {
    private final List<State> states;

    public States() {
        this.states = new ArrayList<>();
    }

    public void add(State state) {
        this.states.add(state);
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
        List<Pin> pins = getPins();
        int size = pins.size();

        if (size == 0) {
            return new Pin(Pin.MIN_PIN);
        }

        return pins.get(size - 1);
    }

    public Pin getBeforePin() {
        List<Pin> pins = getPins();
        int size = pins.size();

        if (size == 0 || size == 1) {
            return new Pin(Pin.MIN_PIN);
        }

        return getLastPreviousPin(pins, size);
    }

    private Pin getLastPreviousPin(List<Pin> pins, int size) {
        return pins.get(size - 2);
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
        return this.states
                .stream()
                .map(state -> new Pin(state.getFallenPins()))
                .collect(Collectors.toList());
    }
}
