package domain.bowling;

import domain.Pins;
import domain.state.State;
import domain.state.StateFactory;

public class SecondSet implements Bowling {

    private final State state;

    private SecondSet(Pins first, Pins second) {
        state = StateFactory.secondState(first, second);
    }

    public static SecondSet of(Pins first, Pins second) {
        return new SecondSet(first, second);
    }

    @Override
    public Bowling bowl(Pins downPins) {
        throw new RuntimeException("더이상 진행 할 수 없습니다.");
    }

    @Override
    public State getFrameState() {
        return state;
    }
}
