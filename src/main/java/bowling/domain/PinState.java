package bowling.domain;

public class PinState {
    public static final int DEFAULT_TRY_COUNT = 0;
    public static final int MAX_TRY_COUNT = 2;

    private int maxTryCount;
    private int tryCount;

    private PinState(int maxTryCount, int tryCount) {
        this.maxTryCount = maxTryCount;
        this.tryCount = tryCount;
    }

    public static PinState of(int maxTryCount, int tryCount) {
        return new PinState(maxTryCount, tryCount);
    }

    public boolean canHit() {
        return tryCount < maxTryCount;
    }

    public boolean isFirstTime() {
        return tryCount == 1;
    }

    public void counting() {
        tryCount++;
    }
}
