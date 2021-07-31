package bowling.domain.state;

import bowling.domain.pin.DownedPin;

import java.util.Objects;

public abstract class State {

    public State downPins(DownedPin downedPin) {
        validate(downedPin);
        return nextState(downedPin);
    }

    private void validate(DownedPin downedPin) {
        if (Objects.isNull(downedPin)) {
            throw new IllegalArgumentException("DownedPin can't be null");
        }
    }

    protected abstract State nextState(DownedPin downedPin);
}
