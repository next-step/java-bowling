package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

import java.util.Stack;
import java.util.stream.Collectors;

public class FinalReady implements State {
    private static final int NORMAL_FRAME_COUNT = 2;
    private static final int MAX_COUNT = 3;

    private Stack<State> states = new Stack<>();
    private int count;

    public FinalReady() { }

    @Override
    public State roll(final Pins knockOverPins) {
        ready();
        State state = states.pop().roll(knockOverPins);
        states.push(state);
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
        if (states.isEmpty() || states.peek().isTurnOver()) {
            states.push(new Ready());
        }
    }

    private boolean isPossibleTurnOver() {
        return count == NORMAL_FRAME_COUNT && !hasBonusState();
    }

    private boolean hasBonusState() {
        return states.stream().anyMatch(Strike.class::isInstance) || states.stream().anyMatch(Spare.class::isInstance);
    }
}
