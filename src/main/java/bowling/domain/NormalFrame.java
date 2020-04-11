package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;

import java.util.Objects;

public class NormalFrame {
    private static final int MAX_PIN_COUNT = 10;
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "핀 카운트는 %d 를 넘을 수 없습니다.";
    private PinCount firstPinCount;
    private PinCount secondPinCount;

    public NormalFrame(PinCount firstPinCount) {
        this.firstPinCount = firstPinCount;
    }

    public void addPinCount(PinCount secondPinCount) {
        if (firstPinCount.add(secondPinCount) > MAX_PIN_COUNT) {
            throw new OutOfRangeArgumentException(
                    String.format(OUT_OF_RANGE_ERROR_MESSAGE, MAX_PIN_COUNT));
        }
        this.secondPinCount = secondPinCount;
    }

    public int getScore() {
        return firstPinCount.add(secondPinCount);
    }

    public boolean isDone() {
        if (!Objects.isNull(firstPinCount) && !Objects.isNull(secondPinCount)) {
            return true;
        }
        assert firstPinCount != null;
        return firstPinCount.equals(new PinCount(MAX_PIN_COUNT));
    }
}
