package bowling.domain.state;

import java.util.Collections;
import java.util.List;

public class LastEnd extends EndState {
    private final ComplexState complexState;

    public LastEnd(ComplexState complexState) {
        this.complexState = complexState;
    }

    public static LastEnd from(ComplexState complexState) {
        return new LastEnd(complexState);
    }

    @Override
    public List<State> getState() {
        return complexState.getState();
    }

    @Override
    public List<Integer> getDownedPins() {
        return Collections.emptyList();
    }
}
