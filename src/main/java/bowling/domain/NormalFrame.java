package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;

public class NormalFrame implements Frame {
    private static final int MAX_PIN_COUNT_SIZE = 2;
    private static final int MAX_PIN_COUNT = 10;
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "핀 카운트는 %d 를 넘을 수 없습니다.";

    private PinCounts pinCounts;
    private Frame next;

    public NormalFrame() {
        pinCounts = new PinCounts(MAX_PIN_COUNT_SIZE);
    }

    @Override public void addPinCount(PinCount pinCount) {
        if (!isAddable(pinCount)) {
            throw new OutOfRangeArgumentException(
                    String.format(OUT_OF_RANGE_ERROR_MESSAGE, MAX_PIN_COUNT));
        }

        if (!isDone()) {
            pinCounts.add(pinCount);
        }
    }

    private boolean isAddable(PinCount pinCount) {
        PinCount firstPinCount = pinCounts.getFirst()
                .orElse(PinCount.empty());
        if(firstPinCount.isMax()) {
            return false;
        }
        return firstPinCount.isOverMaxAfterAdd(pinCount);
    }

    @Override public int getScore() {
        return pinCounts.getPinCountTotal();
    }

    @Override public boolean isDone() {
        if (pinCounts.isFull()) {
            return true;
        }

        return pinCounts.getPinCountTotal() == MAX_PIN_COUNT;
    }

    public Frame createNext() {
        next = new NormalFrame();
        return next;
    }

    public Frame createFinal() {
        next = new FinalFrame();
        return next;
    }
}
