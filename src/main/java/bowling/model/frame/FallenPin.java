package bowling.model.frame;

public class FallenPin {
    private static final int MIN = 0;
    private static final int MAX = 10;

    private final int count;

    private FallenPin(int fallenPinCount) {
        validateRange(fallenPinCount);

        this.count = fallenPinCount;
    }

    public static FallenPin from(int fallenPinCount) {
        return new FallenPin(fallenPinCount);
    }

    private void validateRange(int fallenCount) {
        if (fallenCount < MIN || fallenCount > MAX) {
            throw new IllegalArgumentException("쓰러진 핀은 0개 이상 10개 이하여야 합니다.");
        }
    }

    public int count() {
        return count;
    }

    public boolean isMax() {
        return count == MAX;
    }

    public boolean isMin() {
        return count == MIN;
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}
