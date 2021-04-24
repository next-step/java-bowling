package bowling.domain;

import java.util.List;

public interface PinCounts {

    void knockDown(int pinCount);

    boolean isFinished();

    List<PinCount> pinCounts();

    default int totalPinCount() {
        return pinCounts().stream()
                .reduce(0, (sum, pinCount) -> pinCount.plus(sum), (lastPinCountSum, result) -> result);
    }
}
