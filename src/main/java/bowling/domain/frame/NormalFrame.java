package bowling.domain.frame;

import bowling.exception.BowlingException;

import static bowling.exception.BowlingExceptionCode.*;

public class NormalFrame implements Frame {
    private FrameProperties frameProperties;

    public NormalFrame(int index) {
        frameProperties = new FrameProperties(index);
    }

    public NormalFrame(FrameProperties frameProperties) {
        this.frameProperties = frameProperties;
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
        frameProperties.minusTryNo();
        frameProperties.addPins(fallenPins);
    }

    private boolean excessive(int fallenPins) {
        return 10 < frameProperties.computeSumOfFallenPins() + fallenPins;
    }

    private boolean moveable() {
        return frameProperties.tryNo() < 1 || frameProperties.computeSumOfFallenPins() > 9;
    }

    public int validateMoveToNextIndex() {
        if (moveable()) {
            return frameProperties.index() + 1;
        }
        return frameProperties.index();
    }

    @Override
    public int index() {
        return frameProperties.index();
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "frameProperties=" + frameProperties +
                '}';
    }
}
