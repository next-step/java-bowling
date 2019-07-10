package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class FinalState implements State {

    private int round;
    private List<State> states;

    private FinalState(List<State> states) {
        this.states = new ArrayList<>(states);
    }

    public static State valueOf() {
        List<State> states = Arrays.asList(new None());
        return new FinalState(states);
    }

    @Override
    public State bowl(Pins pins) {
        round++;
        State state = getCurrentState().isFinished() ? new None() : getCurrentState();
        state = state.bowl(pins);
        if (getCurrentState().isFinished()) {
            states.add(state);
        } else {
            states.set(getCurrentStateIndex(), state);
        }
        return state;
    }

    private State getCurrentState() {
        return states.get(getCurrentStateIndex());
    }

    private int getCurrentStateIndex() {
        return states.size() - 1;
    }

    @Override
    public boolean isFinished() {
        return round == 3 || hasNotBonusStage();
    }

    private boolean hasNotBonusStage() {
        return states.stream()
                .anyMatch(Miss.class::isInstance);
    }

    @Override
    public String printResult() {
        String result = states.stream()
                .map(State::printResult)
                .map(String::trim)
                .collect(joining("|"));
        return Pretty.alignCenter(result).concat("|");
    }

    List<State> getStates() {
        return Collections.unmodifiableList(states);
    }
}
