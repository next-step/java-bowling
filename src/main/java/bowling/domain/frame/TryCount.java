package bowling.domain.frame;

public class TryCount {
    private final int tryCount;

    private TryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    public static TryCount of(int tryCount) {
        return new TryCount(tryCount);
    }

    public boolean isMax() {
        return false;
    }
}
