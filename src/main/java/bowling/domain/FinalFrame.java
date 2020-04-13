package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;

import java.util.List;

public class FinalFrame implements Frame {
    private static final int MAX_PIN_COUNT_SIZE = 3;
    private static final int MIN_PIN_COUNT_FOR_THIRD = 10;
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "잘못된 핀 카운트 입니다.";

    private PinCounts pinCounts;

    public FinalFrame() {
        pinCounts = new PinCounts(MAX_PIN_COUNT_SIZE);
    }

    @Override public void addPinCount(int pinCount) {
        if (!isAddable(pinCount)) {
            throw new OutOfRangeArgumentException(OUT_OF_RANGE_ERROR_MESSAGE);
        }

        if (!isDone()) {
            pinCounts.add(pinCount);
        }
    }

    private boolean isAddable(int pinCount) {
        if (pinCounts.size() != 1) {
            return true;
        }
        PinCount firstPinCount = pinCounts.getFirst()
                .orElse(PinCount.empty());

        if (firstPinCount.isMax()) {
            return true;
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
        return pinCounts.size() == 2 &&
                pinCounts.getPinCountTotal() < MIN_PIN_COUNT_FOR_THIRD;
    }

    @Override public List<PinCount> getPinCounts() {
        return pinCounts.getPinCounts();
    }

    @Override public Frame createNext() {
        return null;
    }

    @Override public Frame getNext() {
        return null;
    }

    @Override public boolean isLast() {
        return true;
    }
}
