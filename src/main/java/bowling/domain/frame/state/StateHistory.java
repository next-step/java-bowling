package bowling.domain.frame.state;

import bowling.domain.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StateHistory {
    private final List<State> states;

    public StateHistory() {
        states = new ArrayList<>();
        states.add(new FirstPitch());
    }

    public State getLatestState() {
        return states.get(states.size() - 1);
    }

    public void addState(State state) {
        states.add(state);
    }

    public void calculateScore(Score score) {
        List<State> calculableStates = states.stream()
                .filter(State::isCalculable)
                .collect(Collectors.toList());

        for (State state : calculableStates) {
            score.sum(state.calculateScore());
        }
    }
}
