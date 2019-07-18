package domain.bowling;

import domain.Pins;
import domain.state.Waiting;
import domain.state.State;

public class ReadySet implements Bowling {

    @Override
    public Bowling bowl(Pins downPins) {
        return FirstSet.of(downPins);
    }

    @Override
    public State getFrameState() {
        return new Waiting();
    }
}
