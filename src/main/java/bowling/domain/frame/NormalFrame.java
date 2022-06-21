package bowling.domain.frame;

import bowling.exception.BowlingException;

import static bowling.exception.BowlingExceptionCode.*;

public class NormalFrame implements Frame {
    private final int index;
    private final FallenPins fallenPins;

    private int tryNo;
    private FrameProperties frameProperties;

    public NormalFrame(int index) {
        this(index, 2, new FallenPins());
        frameProperties = new FrameProperties(index);
    }

    public NormalFrame(int index, int tryNo, FallenPins fallenPins) {
        this.index = index;
        this.tryNo = tryNo;
        this.fallenPins = fallenPins;
    }

    public void addPins(int fallenPins) {
        frameProperties.addPins(fallenPins);
    }

    @Override
    public FallenPins pins() {
        return frameProperties.pins();
    }

    @Override
    public void determineSpare(int fallenPins) {
        if (excessive(fallenPins)) {
            throw new BowlingException(INVALID_COUNT_OF_FALLEN_PINS, fallenPins);
        }
        tryNo--;
        this.fallenPins.add(fallenPins);
    }

    private boolean excessive(int fallenPins) {
        return 10 < frameProperties.computeSumOfFallenPins() + fallenPins;
    }

    private boolean moveable() {
        return tryNo < 1 || frameProperties.computeSumOfFallenPins() > 9;
    }

    @Override
    public int validateMoveToNextIndex() {
        if (moveable()) {
            return index + 1;
        }
        return index;
    }

    @Override
    public int index() {
        return frameProperties.index();
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", pins=" + fallenPins +
                '}';
    }
}
