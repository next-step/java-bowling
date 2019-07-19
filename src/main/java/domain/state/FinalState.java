package domain.state;

import domain.Pins;

import java.util.List;
import java.util.stream.Collectors;

public class FinalState implements State {
    private static final String FINAL_FRAME_DELIMITER = "|";

    private List<State> states;

    public FinalState(List<State> states) {
        this.states = states;
    }

    @Override
    public State update(Pins fallenPins) { //TODO: 개선 필요
        return null;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public String printState() {
        return states.stream()
                .map(State::printState)
                .collect(Collectors.joining(FINAL_FRAME_DELIMITER));
    }
}
