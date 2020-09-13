package bowling.domain;

import static bowling.domain.PinState.DEFAULT_TRY_COUNT;
import static bowling.domain.PinState.MAX_TRY_COUNT;

public class Pin {
    public static final int FULL_COUNT = 10;
    public static final int NO_COUNT = 0;

    private PinState state;
    private int count;

    private Pin(int maxTryCount, int tryCount) {
        this.state = PinState.of(maxTryCount, tryCount);
        this.count = FULL_COUNT;
    }

    public static Pin from() {
        return new Pin(MAX_TRY_COUNT, DEFAULT_TRY_COUNT);
    }

    public static Pin of(int maxTryCount, int tryCount) {
        return new Pin(maxTryCount, tryCount);
    }

    public String hit(int count) {
        if (!state.canHit()) {
            throw new IllegalArgumentException("더이상 공을 던질수 없습니다.");
        }

        if (this.count == NO_COUNT || count > this.count) {
            throw new IllegalArgumentException("더이상 쓰러질 핀이 없습니다.");
        }

        state.counting();
        this.count -= count;

        if (this.count == 0 && state.isFirstTime()) {
            return Status.STRIKE.toString();
        }

        if (this.count == 0) {
            return Status.SPARE.toString();
        }

        if (count == 0) {
            return Status.GUTTER.toString();
        }

        return String.valueOf(count);
    }

    public boolean isClear() {
        return this.count == NO_COUNT;
    }

    @Override
    public String toString() {
        return "Pin{" +
                "state=" + state +
                ", count=" + count +
                '}';
    }
}
