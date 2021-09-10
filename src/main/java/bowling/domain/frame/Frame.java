package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;

public abstract class Frame {

    public static final int MAX_ROUND_NUMBER = 10;
    public static final int NEXT_ROUND_NUMBER_DISTANCE = 1;

    protected final int roundNumber;
    protected final Pins pins;
    protected Frame nextFrame;

    protected Frame(final int roundNumber, final Pins pins) {
        this(roundNumber, pins, null);
    }

    protected Frame(int roundNumber, Pins pins, Frame nextFrame) {
        validateFrame(pins);
        this.roundNumber = roundNumber;
        this.pins = pins;
        this.nextFrame = nextFrame;
    }

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    protected abstract void validateFrame(Pins pins);

    public abstract Frame bowl(final int knockDownNumber);

    public abstract boolean isFinished();

    public abstract Frame nextFrame();

    public abstract int getScore();

    public abstract int addScore(int score, int count);

    public abstract boolean canCalculateScore();

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

    @Override
    public String toString() {
        return pins.toString() + roundNumber;
    }
}
