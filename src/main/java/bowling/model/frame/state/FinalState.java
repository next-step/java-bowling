package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.utils.Pretty.PARTITION_OF_SYMBOL;
import static java.util.stream.Collectors.joining;

public class FinalState implements State {

    private static final int MAX_NUMBER_OF_ROUND = 3;
    private static final int LAST_INDEX = 1;

    private int round;
    private List<State> states = new ArrayList<>();

    private FinalState() {
        ready();
    }

    public static State valueOf() {
        return new FinalState();
    }

    private void ready() {
        states.add(None.getInstance());
    }

    @Override
    public State bowl(Pins pins) {
        round++;
        if (getCurrentState().isFinished()) {
            ready();
        }

        State state = getCurrentState().bowl(pins);

        states.set(getLastStateIndex(), state);
        return state;
    }

    private State getCurrentState() {
        return states.get(getLastStateIndex());
    }

    private int getLastStateIndex() {
        return states.size() - LAST_INDEX;
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
                .collect(joining(PARTITION_OF_SYMBOL));
    }

    List<State> getStates() {
        return Collections.unmodifiableList(states);
    }
}
