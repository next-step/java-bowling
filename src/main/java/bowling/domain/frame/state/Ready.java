package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Ready implements State {

    public Ready() { }

    @Override
    public State roll(final Pins knockOverPins) {
        if (knockOverPins.isStrike()) {
            return new Strike();
        }
        return new Running(knockOverPins);
    }
}
