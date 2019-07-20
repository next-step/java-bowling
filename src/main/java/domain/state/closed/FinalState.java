package domain.state.closed;

import domain.state.State;

import java.util.List;
import java.util.stream.Collectors;

public class FinalState extends Closed {
    private static final String FINAL_FRAME_DELIMITER = "|";

    private List<State> states;

    public FinalState(List<State> states) {
        this.states = states;
    }

    @Override
    public String printState() {
        return states.stream()
                .map(State::printState)
                .collect(Collectors.joining(FINAL_FRAME_DELIMITER));
    }
}
