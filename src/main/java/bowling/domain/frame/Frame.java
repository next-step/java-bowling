package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;

public abstract class Frame {

    public static final int MAX_ROUND_NUMBER = 10;

    protected final int roundNumber;
    protected final Pins pins;

    protected Frame(final int roundNumber, final Pins pins) {
        this.roundNumber = roundNumber;
        this.pins = pins;
    }

    public abstract void inputScore(final int score);

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
