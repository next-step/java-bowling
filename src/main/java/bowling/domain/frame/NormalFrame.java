package bowling.domain.frame;

import bowling.exception.BowlingException;

import java.util.Objects;

import static bowling.exception.BowlingExceptionCode.INVALID_COUNT_OF_FALLEN_PINS;

public class NormalFrame implements Frame {
    private static final int FINAL_INDEX = 10;

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

    @Override
    public Frame validateMoveToNextFrame() {
        if (!moveable()) {
            return this;
        }
        int nextIndex = frameProperties.index() + 1;
        if (nextIndex >= FINAL_INDEX) {
            return new FinalFrame(FINAL_INDEX);
        }
        return new NormalFrame(nextIndex);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameProperties.equals(that.frameProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameProperties);
    }
}
