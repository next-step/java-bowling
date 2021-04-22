package bowling.domain.frame;

import java.util.Objects;

public class TryCount {
    private static final int MAX_TRY_HIT = 2;
    private static final int FIRST_TRY = 0;

    private final int tryCount;

    private TryCount(int tryCount) {
        validate(tryCount);
        this.tryCount = tryCount;
    }

    public static TryCount of(int tryCount) {
        return new TryCount(tryCount);
    }

    private void validate(int tryCount) {
        if (tryCount < FIRST_TRY || tryCount > MAX_TRY_HIT) {
            throw new IllegalArgumentException("유효한 시도 횟수가 아닙니다.");
        }
    }

    public boolean isFirstHit() {
        return this.tryCount == FIRST_TRY;
    }

    public boolean isMaxHit() {
        return this.tryCount == MAX_TRY_HIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TryCount tryCount1 = (TryCount)o;
        return tryCount == tryCount1.tryCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tryCount);
    }
}
