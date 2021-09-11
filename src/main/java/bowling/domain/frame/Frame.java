package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public abstract class Frame {

    public static final int MAX_ROUND_NUMBER = 10;
    public static final int NEXT_ROUND_NUMBER_DISTANCE = 1;

    protected final int roundNumber;
    protected final Pins pins;
    protected Frame nextFrame;

    protected Frame(final int roundNumber, final Pins pins) {
        this(roundNumber, pins, null);
    }

    protected Frame(int roundNumber, Pins pins, final Frame nextFrame) {
        validateFrame(pins);
        this.roundNumber = roundNumber;
        this.pins = pins;
        this.nextFrame = nextFrame;
    }

    protected void setNextFrame(final Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    protected abstract void validateFrame(final Pins pins);

    public abstract boolean isFinished();

    public abstract Frame bowl(final int knockDownNumber);

    public abstract Frame next();

    public abstract boolean canCalculateScore();

    public abstract int getScore();

    public abstract int addScore(final Score score);

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

    protected int nextPinSize() {
        if (nextFrame == null) {
            return 0;
        }
        return nextFrame.pinSize();
    }

    public int getRoundNumber() {
        return roundNumber;
    }
}
