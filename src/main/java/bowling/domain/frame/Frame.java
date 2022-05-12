package bowling.domain.frame;

import bowling.domain.pin.PinNumbers;
import bowling.domain.pin.PinStatus;

import static bowling.domain.pin.PinStatus.*;

public abstract class Frame {

    protected PinNumbers pinNumbers;

    protected Frame(int firstNo, int secondNo) {
        this.pinNumbers = new PinNumbers(firstNo, secondNo);
    }

    public abstract Frame next(int firstNo, int secondNo);

    public boolean isStrike() {
        return getStatus() == STRIKE;
    }

    public boolean isSpare() {
        return getStatus() == SPARE;
    }

    public PinStatus getStatus() {
        return pinNumbers.getStatus();
    }

    public int getFirstNo() {
        return pinNumbers.getFirstNo();
    }

    public int getSecondNo() {
        return pinNumbers.getSecondNo();
    }
}
