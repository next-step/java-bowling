package bowling.domain.frame;

import bowling.exception.BowlingException;

import static bowling.exception.BowlingExceptionCode.INVALID_COUNT_OF_FALLEN_PINS;

public class FinalFrame implements Frame {
    private final int index;
    private int tryNo;
    private final FallenPins fallenPins;
    private int totalFallenPins;

    private int bonus = 1;
    private FrameProperties frameProperties;

    public FinalFrame(int index) {
        this(index, 2, new FallenPins(), 0);
        frameProperties = new FrameProperties(index);
    }

    public FinalFrame(int index, int tryNo, FallenPins pins, int totalFallenPins) {
        this.index = index;
        this.tryNo = tryNo;
        this.fallenPins = pins;
        this.totalFallenPins = totalFallenPins;
    }

    @Override
    public void determineSpare(int fallenPins) {
        if (excessive(fallenPins)) {
            throw new BowlingException(INVALID_COUNT_OF_FALLEN_PINS, fallenPins);
        }
        totalFallenPins += fallenPins;
        tryNo--;
        if (isBonus()) {
            tryNo += bonus--;
            totalFallenPins = 0; // TODO(jack.comeback) : fallenPins의 합을 0으로 만들어야 함.
        }
        this.fallenPins.add(fallenPins);
    }

    private boolean excessive(int fallenPins) {
        return 10 < totalFallenPins + fallenPins;
    }

    private boolean isBonus() {
        return tryNo < 2 && totalFallenPins == 10;
    }

    @Override
    public FallenPins pins() {
        return frameProperties.pins();
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
        return frameProperties.index();
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "index=" + index +
                ", pins=" + fallenPins +
                '}';
    }
}
