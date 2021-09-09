package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;

public abstract class Frame {

    public static final int MAX_ROUND_NUMBER = 10;
    public static final int NEXT_ROUND_NUMBER_DISTANCE = 1;

    protected final int roundNumber;
    protected final Pins pins;

    protected Frame(final int roundNumber, final Pins pins) {
        validateFrame(pins);
        this.roundNumber = roundNumber;
        this.pins = pins;
    }

    protected abstract void validateFrame(Pins pins);

    public abstract Frame bowl(final int knockDownNumber);

    public abstract boolean isFinished();

    public abstract Frame nextFrame();

    public Pin firstPin() {
        return pins.firstPin();
    }

    public Pin secondPin() {
        return pins.secondPin();
    }

    public Pin thirdPin() {
        return pins.thirdPin();
    }

    public int pinSize() {
        return pins.size();
    }

    public int getRoundNumber() {
        return roundNumber;
    }
}
