package bowling.domain.frame.state;

import bowling.domain.Pins;

public class Ready implements State {

    @Override
    public State bowl(final int pinsCount) {
        Pins pins = Pins.from().bowl(pinsCount);

        if (pins.isFinish()) {
            return new Strike();
        }

        return new FirstBowl(pins);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String getCurrentPinsState() {
        return null;
    }
}
