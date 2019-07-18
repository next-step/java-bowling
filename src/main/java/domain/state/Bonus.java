package domain.state;

import domain.Pins;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.OutputResult.SYMBOL_DELIMITER;

public class Bonus implements State {

    private final static int BONUS_FRAME_BOWL_LIMIT = 3;
    private final static int SECOND_BOWL = 2;

    private List<Pins> pins;
    private List<State> states;

    public Bonus() {
        pins = new ArrayList<>();
        states = new ArrayList<>();
        states.add(new Waiting());
    }

    @Override
    public boolean isClosed() {
        return isLastBowl() || isEmptyWhenSecondBowl();
    }

    @Override
    public String toSymbol() {
        List<String> result = states.stream()
                .filter(State::isClosed)
                .map(State::toSymbol)
                .collect(Collectors.toList());
        return String.join(SYMBOL_DELIMITER, result);
    }

    private State getState() {
        State state = states.get(states.size() - 1);
        return state.isClosed() ? new Waiting() : state;
    }

    private boolean isLastBowl() {
        return pins.size() == BONUS_FRAME_BOWL_LIMIT;
    }

    private boolean isEmptyWhenSecondBowl() {
        return pins.size() == SECOND_BOWL && addAll() < Pins.ALL.value();
    }

    private int addAll() {
        return this.pins.stream()
                .mapToInt(Pins::value)
                .sum();
    }
}
