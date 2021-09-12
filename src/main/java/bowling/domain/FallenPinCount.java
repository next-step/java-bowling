package bowling.domain;

public class FallenPinCount {

    private static final int MINIMUM_FALLEN_PIN_COUNT = 0;
    private static final int MAXIMUM_FALLEN_PIN_COUNT = 10;

    private final int count;

    public FallenPinCount(int count) {
        validatePinCountRange(count);
        this.count = count;
    }

    private void validatePinCountRange(int count) {
        if (count < MINIMUM_FALLEN_PIN_COUNT || count > MAXIMUM_FALLEN_PIN_COUNT) {
            throw new IllegalArgumentException(String.format("쓰러진 핀의 수는 0에서 10 사이의 값이어야 합니다. count: %s", count));
        }
    }

    public int count() {
        return count;
    }

    public boolean isSparedWith(FallenPinCount second) {
        int total = this.count + second.count;
        if (total > MAXIMUM_FALLEN_PIN_COUNT) {
            throw new IllegalArgumentException(String.format("쓰러트린 핀의 수가 유효하지 않습니다. first: %s, second: %s", this.count, second.count));
        }
        return total == MAXIMUM_FALLEN_PIN_COUNT;
    }
}
