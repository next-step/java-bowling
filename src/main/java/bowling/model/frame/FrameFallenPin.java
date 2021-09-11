package bowling.model.frame;

public class FrameFallenPin {
    private static final int MIN_TOTAL = 0;
    private static final int MAX_TOTAL = 10;
    private static final int NO_FALLEN_PIN = 0;
    private static final FallenPin EMPTY = null;

    private final FallenPin first;
    private final FallenPin second;

    public FrameFallenPin(FallenPin first, FallenPin second) {
        this.first = first;
        this.second = second;
    }

    public FrameFallenPin(FallenPin first) {
        this.first = first;
        this.second = EMPTY;
    }

    public FrameFallenPin(int firstFallenPinCount) {
        this.first = FallenPin.of(firstFallenPinCount);
        this.second = EMPTY;
    }

    public FrameFallenPin(int firstFallenPinCount, int secondFallenPinCount) {
        this.first = FallenPin.of(firstFallenPinCount);
        this.second = FallenPin.of(secondFallenPinCount);
    }

    public static FrameFallenPin first(int firstFallenPinCount) {
        return new FrameFallenPin(FallenPin.of(firstFallenPinCount));
    }

    public FrameFallenPin second(int secondFallenPinCount) {
        validateNotStrike();
        validateSecondFallenPinCountRange(first.count(), secondFallenPinCount);

        return new FrameFallenPin(first, FallenPin.of(secondFallenPinCount));
    }

    private void validateNotStrike() {
        if (isStrike()) {
            throw new IllegalArgumentException("이미 스트라이크이기 때문에 두 번째로 쓰러진 핀을 생성할 수 없습니다.");
        }
    }

    private void validateSecondFallenPinCountRange(int firstFallenPinCount, int secondFallenPinCount) {
        int fallenPinCountTotal = firstFallenPinCount + secondFallenPinCount;
        if (fallenPinCountTotal > MAX_TOTAL) {
            throw new IllegalArgumentException(String.format("총 쓰러진 핀의 개수가 %d개를 초과할 수 없습니다.", MAX_TOTAL));
        }
    }

    public boolean isStrike() {
        return first.isMax() && second == EMPTY;
    }

    public boolean isSpare() {
        if (!pitchTwice()) {
            return false;
        }

        int fallenPinCountTotal = first.count() + second.count();
        return fallenPinCountTotal == MAX_TOTAL;
    }

    public boolean isMiss() {
        if (!pitchTwice()) {
            return false;
        }

        int fallenPinCountTotal = first.count() + second.count();
        return fallenPinCountTotal > MIN_TOTAL && fallenPinCountTotal < MAX_TOTAL;
    }

    public boolean pitchTwice() {
        return first != EMPTY && second != EMPTY;
    }

    public boolean isEmpty() {
        return first == EMPTY;
    }

    public boolean isFirst() {
        return first != EMPTY && second == EMPTY;
    }

    public FallenPin first() {
        return first;
    }

    public FallenPin second() {
        return second;
    }

    public boolean isFirstAndGutter() {
        if (first == EMPTY) {
            return false;
        }
        return first.isMin() && second == EMPTY;
    }

    public boolean isDoubleGutter() {
        if (first == EMPTY || second == EMPTY) {
            return false;
        }
        return first.isMin() && second.isMin();
    }

    public int firstCount() {
        if (first == EMPTY) {
            return NO_FALLEN_PIN;
        }
        return first.count();
    }

    public int secondCount() {
        if (second == EMPTY) {
            return NO_FALLEN_PIN;
        }
        return second.count();
    }

    public int countTotal() {
        if (first == EMPTY) {
            return MIN_TOTAL;
        }

        if (second == EMPTY) {
            return first.count();
        }

        return first.count() + second.count();
    }
}
