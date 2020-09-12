package bowling.domain;

public class Pin {
    public static final int DEFAULT_TRY_COUNT = 0;
    public static final int DEFAULT_PIN_COUNT = 10;
    public static final int MAX_TRY_COUNT = 2;
    public static final int NO_COUNT = 0;

    private int maxTryCount;
    private int tryCount;
    private int count;

    private Pin(int maxTryCount, int tryCount) {
        this.maxTryCount = maxTryCount;
        this.tryCount = tryCount;
        this.count = DEFAULT_PIN_COUNT;
    }

    public static Pin from() {
        return new Pin(MAX_TRY_COUNT, DEFAULT_TRY_COUNT);
    }

    public static Pin of(int maxTryCount, int tryCount) {
        return new Pin(maxTryCount, tryCount);
    }

    public String hit(int count) {
        tryCount++;

        if (tryCount > this.maxTryCount)
            throw new IllegalArgumentException("더이상 공을 던질수 없습니다.");

        if (this.count == NO_COUNT || count > this.count)
            throw new IllegalArgumentException("더이상 쓰러질 핀이 없습니다.");

        this.count -= count;

        if (this.count == 0 && tryCount == 1) {
            return "X";
        }

        if (this.count == 0) {
            return "/";
        }

        if (count == 0) {
            return "-";
        }

        return String.valueOf(count);
    }

    public boolean isFinish() {
        if (this.tryCount == this.maxTryCount)
            return true;

        if (this.count == NO_COUNT)
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "Pin{" +
                "maxTryCount=" + maxTryCount +
                ", tryCount=" + tryCount +
                ", count=" + count +
                '}';
    }
}
