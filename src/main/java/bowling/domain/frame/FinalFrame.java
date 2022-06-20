package bowling.domain.frame;

import bowling.exception.BowlingException;

import static bowling.exception.BowlingExceptionCode.INVALID_COUNT_OF_FALLEN_PINS;

public class FinalFrame implements Frame {
    private final int index;
    private final Pins pins;

    private int fallenPins;
    private int tryNo;
    private int bonus = 1;

    public FinalFrame(int index) {
        this(index, 2, 0, new Pins());
    }

    public FinalFrame(int index, int tryNo, int fallenPins, Pins pins) {
        this.index = index;
        this.tryNo = tryNo;
        this.fallenPins = fallenPins;
        this.pins = pins;
    }

    @Override
    public void determineSpare(int fallenPins) {
        if (excessive(fallenPins)) {
            throw new BowlingException(INVALID_COUNT_OF_FALLEN_PINS, fallenPins);
        }
        this.fallenPins += fallenPins;
        tryNo--;
        if (isBonus()) {
            tryNo += bonus--;
            this.fallenPins = 0;
        }
        pins.add(fallenPins);
    }

    private boolean excessive(int fallenPins) {
        return 10 < this.fallenPins + fallenPins;
    }

    private boolean isBonus() {
        return tryNo < 2 && fallenPins == 10;
    }

    @Override
    public Pins pins() {
        return pins;
    }

    private boolean moveable() {
        return tryNo < 1;
    }

    @Override
    public int validateMoveToNextIndex() {
        if (moveable()) { // 다 던지면 끝
            return index + 1;
        }
        return index;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "index=" + index +
                ", pins=" + pins +
                '}';
    }
}
