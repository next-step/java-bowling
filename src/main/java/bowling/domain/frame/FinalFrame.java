package bowling.domain.frame;

import bowling.exception.BowlingException;

import java.util.Objects;

import static bowling.exception.BowlingExceptionCode.INVALID_COUNT_OF_FALLEN_PINS;

public class FinalFrame implements Frame {
    private final FrameProperties frameProperties;
    private int totalFallenPins;
    private int bonus = 1;

    public FinalFrame(int index) {
        frameProperties = new FrameProperties(index);
    }
    public FinalFrame(FrameProperties frameProperties) {
        this.frameProperties = frameProperties;
    }

    @Override
    public void determineSpare(int fallenPins) {
        if (excessive(fallenPins)) {
            throw new BowlingException(INVALID_COUNT_OF_FALLEN_PINS, fallenPins);
        }
        totalFallenPins += fallenPins;
        frameProperties.minusTryNo();
        if (isBonus()) {
            frameProperties.plusTryNo(bonus--);
            totalFallenPins = 0;
        }
        frameProperties.addPins(fallenPins);
    }

    private boolean excessive(int fallenPins) {
        return 10 < totalFallenPins + fallenPins;
    }

    private boolean isBonus() {
        return frameProperties.tryNo() < 2 && totalFallenPins == 10;
    }

    private boolean moveable() {
        return frameProperties.tryNo() < 1;
    }

    @Override
    public FallenPins pins() {
        return frameProperties.pins();
    }

    @Override
    public Frame validateMoveToNextFrame() {
        if (moveable()) { // 다 던지면 끝
            return new FinalFrame(frameProperties.index() + 1);
        }
        return this;
    }

    @Override
    public int index() {
        return frameProperties.index();
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "frameProperties=" + frameProperties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return totalFallenPins == that.totalFallenPins && isBonus() == that.isBonus() && frameProperties.equals(that.frameProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameProperties, totalFallenPins, isBonus());
    }
}
