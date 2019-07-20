package domain.bowling;

import domain.Pins;
import domain.state.State;
import domain.state.Waiting;

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
