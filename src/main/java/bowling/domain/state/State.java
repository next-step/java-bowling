package bowling.domain.state;

import bowling.domain.pin.DownedPin;

public abstract class State {

    public State downPins(DownedPin downedPin) {
        return nextState(downedPin);
    }

    protected abstract State nextState(DownedPin downedPin);
}
