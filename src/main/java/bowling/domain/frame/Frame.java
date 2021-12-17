package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.pin.Pin;

public class Frame {

    public static final int MAX_FRAME_NUMBER = 10;
    private static final int MIN_FRAME_NUMBER = 1;

    private final int number;
    private Bowl bowl;

    public Frame(Bowl bowl) {
        this(MIN_FRAME_NUMBER, bowl);
    }

    public Frame(int number, Bowl bowl) {
        checkRangeOfNumber(number);
        this.number = number;
        this.bowl = bowl;
    }

    private void checkRangeOfNumber(int number) {
        if (number < MIN_FRAME_NUMBER || number > MAX_FRAME_NUMBER) {
            throw new IllegalFrameNumberException();
        }
    }

    public static Frame firstOf(Bowl bowl) {
        return new Frame(bowl);
    }

    public Frame nextOf(Bowl bowl) {
        return new Frame(number + 1, bowl);
    }

    public boolean pitch(Pin pin) {
        bowl = bowl.pitch(pin);
        return bowl.canPitch();
    }

    public int getNumber() {
        return number;
    }
}
