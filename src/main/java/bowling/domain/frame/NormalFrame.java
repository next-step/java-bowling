package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.BowlFactory;
import bowling.domain.pin.Pin;

public class NormalFrame implements Frame {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;
    private static final int NUMBER_UNIT = 1;

    private final int number;
    private Bowl bowl;

    private NormalFrame(int number) {
        this.number = number;
        this.bowl = BowlFactory.firstBall();
    }

    public static Frame first() {
        return new NormalFrame(MIN_NUMBER);
    }

    @Override
    public Frame next() {
        if (number == MAX_NUMBER) {
            return new FinalFrame();
        }
        return new NormalFrame(number + NUMBER_UNIT);
    }

    @Override
    public boolean pitch(Pin pin) {
        bowl = bowl.pitch(pin);
        return bowl.canPitch();
    }

    @Override
    public boolean hasNextFrame() {
        return true;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
