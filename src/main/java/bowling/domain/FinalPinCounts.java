package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FinalPinCounts implements PinCounts {
    private static final int PIN_COUNTS_MAX_SIZE = 3;
    private static final int SPARE = 10;
    private static final int FIRST_PIN_COUNT_INDEX = 0;
    private static final int SECOND_PIN_COUNT_INDEX = 1;
    private static final String CHECK_SECOND_PIN_COUNT_SPARE_BOUND = "총 쓰러뜨린 개수가 10을 초과하는 지 확인해주세요.";
    private static final String CANNOT_THROW_THIRD = "초구 스트라이크 또는 2구 스페어가 아니기 때문에 3구를 던질 수 없습니다.";
    private static final String CHECK_THIRD_PIN_COUNT_SPARE_BOUND = "2구, 3구에서 총 쓰러뜨린 개수가 10개를 초과하는 지 확인해주세요.";
    private static final String CANNOT_THROW_ANYMORE = "해당 프레임의 모든 공을 이미 던지셨습니다.";

    private final List<PinCount> pinCounts;

    public FinalPinCounts() {
        this(new ArrayList<>());
    }

    private FinalPinCounts(List<PinCount> pinCounts) {
        this.pinCounts = pinCounts;
    }

    @Override
    public void knockDown(int pinCount) {
        checkAllThrown();

        PinCount knockedDownPinCount = new PinCount(pinCount);

        if (pinCounts.isEmpty()) {
            pinCounts.add(knockedDownPinCount);
            return;
        }

        if (pinCounts.size() == 1 && !isOverPinCountForSecondBowlSpare(pinCount)) {
            pinCounts.add(knockedDownPinCount);
            return;
        }

        checkThirdBowlAvailable();

        if (pinCounts.size() == 2 && !isOverPinCountForThirdBowlSpare(pinCount)) {
            pinCounts.add(knockedDownPinCount);
        }
    }

    private boolean isOverPinCountForSecondBowlSpare(int pinCount) {
        if (!isFirstBowlStrike() && addedPinCount(pinCount, FIRST_PIN_COUNT_INDEX) > SPARE) {
            throw new IllegalArgumentException(CHECK_SECOND_PIN_COUNT_SPARE_BOUND);
        }

        return false;
    }

    private int addedPinCount(int pinCount, int index) {
        return pinCounts.get(index).plus(pinCount);
    }

    private void checkThirdBowlAvailable() {
        if (!isThirdBowlAvailable()) {
            throw new IllegalArgumentException(CANNOT_THROW_THIRD);
        }
    }

    private boolean isThirdBowlAvailable() {
        return pinCounts.size() == 2 && (isFirstBowlStrike() || isSecondBowlSpare());
    }

    private boolean isOverPinCountForThirdBowlSpare(int pinCount) {
        if (isFirstBowlStrike() && !isSecondBowlStrike() && addedPinCount(pinCount, SECOND_PIN_COUNT_INDEX) > SPARE) {
            throw new IllegalArgumentException(CHECK_THIRD_PIN_COUNT_SPARE_BOUND);
        }

        return false;
    }

    private void checkAllThrown() {
        if (pinCounts.size() >= PIN_COUNTS_MAX_SIZE) {
            throw new IllegalArgumentException(CANNOT_THROW_ANYMORE);
        }
    }

    private boolean isFirstBowlStrike() {
        return pinCounts.get(FIRST_PIN_COUNT_INDEX).isStrike();
    }

    private boolean isSecondBowlSpare() {
        return !isFirstBowlStrike() && pinCounts.get(SECOND_PIN_COUNT_INDEX).isSpare(pinCounts.get(FIRST_PIN_COUNT_INDEX));
    }

    protected boolean isSecondBowlStrike() {
        return pinCounts.get(SECOND_PIN_COUNT_INDEX).isStrike();
    }

    @Override
    public List<PinCount> pinCounts() {
        return Collections.unmodifiableList(pinCounts);
    }

    @Override
    public boolean isFinished() {
        if (pinCounts.isEmpty()
                || (pinCounts.size() == 1)
                || isThirdBowlAvailable()) {
            return false;
        }
        return !isThirdBowlAvailable() || isAllThrown();
    }

    private boolean isAllThrown() {
        return pinCounts.size() == PIN_COUNTS_MAX_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalPinCounts that = (FinalPinCounts) o;
        return Objects.equals(pinCounts, that.pinCounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCounts);
    }
}
