package domain.bowling;

import domain.Pins;
import domain.state.State;
import domain.state.StateFactory;

public class FirstSet implements Bowling {

    private final Pins first;
    private final State state;

    private FirstSet(Pins first) {
        this.first = first;
        state = StateFactory.firstState(first);
    }

    public static Bowling of(Pins downPins) {
        return new FirstSet(downPins);
    }

    @Override
    public Bowling bowl(Pins downPins) {
        return SecondSet.of(first, downPins);
    }

    @Override
    public State getFrameState() {
        return state;
    }
}
