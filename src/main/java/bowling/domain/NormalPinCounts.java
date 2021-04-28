package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NormalPinCounts implements PinCounts {
    private static final int NORMAL_PIN_COUNTS_SIZE_MAX = 2;
    private static final String CHECK_BOUND = "총 쓰러뜨린 개수가 10을 초과하는 지 확인해주세요.";
    private static final String CANNOT_THROW_SECOND_BOWL = "초구가 스트라이크이기 때문에 2구를 던질 수 없습니다.";
    private static final String CANNOT_THROW_BOWL_ANYMORE = "해당 프레임의 모든 공을 이미 던지셨습니다.";

    private final List<PinCount> pinCounts;

    public NormalPinCounts() {
        this(new ArrayList<>());
    }

    public NormalPinCounts(List<PinCount> pinCounts) {
        if (!pinCounts.isEmpty()) {
            checkBound(pinCounts);
        }

        this.pinCounts = pinCounts;
    }

    private void checkBound(List<PinCount> pinCounts) {
        Integer totalPinCount = pinCounts.stream()
                .reduce(0, (total, pinCount) -> pinCount.plus(total), (lastPinCount, result) -> result);

        if (totalPinCount > TOTAL_PIN_COUNT_MAX) {
            throw new IllegalArgumentException(CHECK_BOUND);
        }
    }

    public void knockDown(int pinCount) {
        checkAllThrown();
        checkStrike();
        checkSpareBound(pinCount);

        PinCount knockedDownPinCount = new PinCount(pinCount);
        pinCounts.add(knockedDownPinCount);
    }

    private void checkAllThrown() {
        if (pinCounts.size() >= NORMAL_PIN_COUNTS_SIZE_MAX) {
            throw new IllegalArgumentException(CANNOT_THROW_BOWL_ANYMORE);
        }
    }

    private void checkStrike() {
        if (isPinCountsSizeOne()
                && isFirstPinCountStrike()) {
            throw new IllegalArgumentException(CANNOT_THROW_SECOND_BOWL);
        }
    }

    private void checkSpareBound(int pinCount) {
        if (isPinCountsSizeOne()
                && !isFirstPinCountStrike()
                && firstPinCount().plus(pinCount) > TOTAL_PIN_COUNT_MAX) {
            throw new IllegalArgumentException(CHECK_BOUND);
        }
    }

    private boolean isPinCountsSizeOne() {
        return pinCounts.size() == PIN_COUNTS_SINGLE_SIZE;
    }

    @Override
    public List<PinCount> pinCounts() {
        return Collections.unmodifiableList(pinCounts);
    }

    @Override
    public boolean isFinished() {
        if (pinCounts.isEmpty()
                || (isPinCountsSizeOne() && !isFirstPinCountStrike())) {
            return false;
        }

        return isFirstPinCountStrike() ||
                (pinCounts.size() == NORMAL_PIN_COUNTS_SIZE_MAX);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalPinCounts that = (NormalPinCounts) o;
        return Objects.equals(pinCounts, that.pinCounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCounts);
    }
}
