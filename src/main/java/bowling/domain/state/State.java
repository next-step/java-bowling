package bowling.domain.state;

import bowling.domain.pin.DownedPins;

import java.util.Objects;

public abstract class State {

    public State downPins(DownedPins downedPins) {
        validate(downedPins);

        return nextState(downedPins);
    }

    private void validate(DownedPins downedPins) {
        if (Objects.isNull(downedPins)) {
            throw new IllegalArgumentException("DownedPin can't be null");
        }
    }

    protected abstract State nextState(DownedPins downedPins);
}
