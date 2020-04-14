package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalReady implements State {
    private static final int LAST_INDEX = 1;
    private static final int NORMAL_FRAME_COUNT = 2;
    private static final int MAX_COUNT = 3;

    private List<State> states = new ArrayList<>();
    private int count;

    public FinalReady() { }

    @Override
    public State roll(final Pins knockOverPins) {
        ready();
        State state = current().roll(knockOverPins);
        states.remove(states.size() - LAST_INDEX);
        states.add(state);
        count++;
        return state;
    }

    @Override
    public boolean isTurnOver() {
        return count == MAX_COUNT || isPossibleTurnOver();
    }

    @Override
    public String toResult() {
        if (states.isEmpty()) {
            return "";
        }
        return states.stream()
                     .map(State::toResult)
                     .collect(Collectors.joining(State.DELIMITER));
    }

    private void ready() {
        if (states.isEmpty() || current().isTurnOver()) {
            states.add(new Ready());
        }
    }

    private boolean isPossibleTurnOver() {
        return count == NORMAL_FRAME_COUNT && !hasBonusState();
    }

    private State current() {
        return states.get(states.size() - LAST_INDEX);
    }

    private boolean hasBonusState() {
        return states.stream().anyMatch(Strike.class::isInstance) || states.stream().anyMatch(Spare.class::isInstance);
    }
}
