package bowling.refactor;

public class LeftScoreCount {

    private final int count;

    private LeftScoreCount(final int count) {
        validateCountRange(count);
        this.count = count;
    }

    private static void validateCountRange(final int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Left Score count must be greater than zero");
        }
    }

    public static LeftScoreCount of(final int count) {
        return new LeftScoreCount(count);
    }

    public static LeftScoreCount of(final LeftScoreCount leftScoreCount) {
        return new LeftScoreCount(leftScoreCount.count);
    }

    public boolean isEqualTo(final int count) {
        return this.count == count;
    }

    public LeftScoreCount minus() {
        return new LeftScoreCount(count - 1);
    }
}
