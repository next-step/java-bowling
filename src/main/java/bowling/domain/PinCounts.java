package bowling.domain;

import java.util.List;

public interface PinCounts {
    int PIN_COUNTS_EMPTY = 0;
    int PIN_COUNTS_SINGLE_SIZE = 1;
    int TOTAL_PIN_COUNT_MAX = 10;

    void knockDown(int pinCount);

    boolean isFinished();

    List<PinCount> pinCounts();

    default int totalPinCount() {
        return pinCounts().stream()
                .reduce(0, (sum, pinCount) -> pinCount.plus(sum), (lastPinCountSum, result) -> result);
    }

    default boolean isFirstPinCountStrike() {
        return pinCounts().get(0).isStrike();
    }

    default boolean isSecondPinCountSpare() {
        return !isFirstPinCountStrike() && pinCounts().get(1).isSpare(pinCounts().get(0));
    }

    default PinCount firstPinCount() {
        return pinCounts().get(0);
    }

    default PinCount secondPinCount() {
        return pinCounts().get(1);
    }
}
