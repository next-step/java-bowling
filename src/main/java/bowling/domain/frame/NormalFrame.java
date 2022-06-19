package bowling.domain.frame;

import bowling.exception.BowlingException;

import static bowling.exception.BowlingExceptionCode.*;

public class NormalFrame implements Frame {
    private final int index;
    private final Pins pins;

    private int fallenPins;
    private int tryNo;

    public NormalFrame(int index) {
        this(index, 2, new Pins());
    }

    public NormalFrame(int index, int tryNo, Pins pins) {
        this.index = index;
        this.tryNo = tryNo;
        this.pins = pins;
    }

    public void addPins(int pins) {
        this.pins.add(pins);
    }

    @Override
    public Pins pins() {
        return pins;
    }

    @Override
    public void determineSpare(int fallenPins) {
        if (excessive(fallenPins)) {
            throw new BowlingException(INVALID_COUNT_OF_FALLEN_PINS, fallenPins);
        }
        this.fallenPins += fallenPins;
        tryNo--;
        pins.add(fallenPins);
    }

    private boolean excessive(int fallenPins) {
        return 10 < this.fallenPins + fallenPins;
    }

    @Override
    public int validateMoveToNextIndex() {
        if (moveable()) {
            return index + 1;
        }
        return index;
    }

    private boolean moveable() {
        return tryNo < 1 || fallenPins > 9;
    }

    @Override
    public boolean equal(int index) {
        return this.index == index;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", pins=" + pins +
                '}';
    }
}
