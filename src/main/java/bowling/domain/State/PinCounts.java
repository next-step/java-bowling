package bowling.domain.State;

import java.util.ArrayList;
import java.util.List;

public class PinCounts {

    private static final int MAX_TOTAL_COUNT = 10;

    private static final int MAX_PIN_COUNTS_SIZE = 2;

    private final List<PinCount> pinCounts;

    public PinCounts(List<PinCount> pinCounts) {
        validatePinCountSize(pinCounts);
        validateTotalPinCount(pinCounts);
        this.pinCounts = new ArrayList<>(pinCounts);
    }

    private void validateTotalPinCount(List<PinCount> pinCounts) {
        if (totalCount(pinCounts) > MAX_TOTAL_COUNT) {
            throw new IllegalArgumentException("투구 핀수가 너무 많습니다.");
        }
    }

    private void validatePinCountSize(List<PinCount> pinCounts) {
        if (pinCounts.size() != MAX_PIN_COUNTS_SIZE) {
            throw new IllegalArgumentException("투구수가 맞지 않습니다.");
        }
    }

    public boolean isSpare() {
        return totalCount(this.pinCounts) == MAX_TOTAL_COUNT;
    }

    public int totalCount() {
        return totalCount(this.pinCounts);
    }

    private int totalCount(List<PinCount> pinCounts) {
        return pinCounts.stream()
                .reduce(0, (sum, pinCount) -> pinCount.count() + sum,
                        Integer::sum);
    }

    public PinCount firstPinCount() {
        return pinCounts.get(0);
    }

    public PinCount secondPinCount() {
        return pinCounts.get(1);
    }


}
