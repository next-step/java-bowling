package domain.bowling;

import domain.Pins;
import domain.state.Waiting;
import domain.state.State;

public class ReadySet implements Bowling {

    private Pins first;

    @Override
    public Bowling bowl(Pins downPins) {
        this.first = downPins;
        return FirstSet.of(downPins);
    }

    @Override
    public State getFrameState() {
        return new Waiting(first);
    }
}
