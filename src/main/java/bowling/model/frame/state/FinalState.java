package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class FinalState implements State {

    private static final int MAX_NUMBER_OF_ROUND = 3;

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

        State state = getCurrentState();
        if(getCurrentState().isFinished()){
            state = new None();
        }
        state = state.bowl(pins);

        if (getCurrentState().isFinished()) {
            states.add(state);
        }
        if (!getCurrentState().isFinished()) {
            states.set(getLastStateIndex(), state);
        }
        return state;
    }

    private State getCurrentState() {
        return states.get(getLastStateIndex());
    }

    private int getLastStateIndex() {
        return states.size() - 1;
    }

    @Override
    public boolean isFinished() {
        return hasNotBonusStage() || MAX_NUMBER_OF_ROUND == round;
    }

    private boolean hasNotBonusStage() {
        return states.stream()
                .anyMatch(Miss.class::isInstance);
    }

    @Override
    public String printResult() {
        return states.stream()
                .map(State::printResult)
                .collect(joining("|"));
    }

    List<State> getStates() {
        return Collections.unmodifiableList(states);
    }
}
