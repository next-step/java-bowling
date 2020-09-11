package bowling.domain;

import java.util.Objects;

public class Result {
    public static final int STRIKE_COUNT = 10;
    public static final int SPARE_COUNT = STRIKE_COUNT;
    public static final int NO_COUNT = 0;

    private Status status;
    private int count;

    Result(Status status, int count) {
        this.status = status;
        this.count = count;
    }

    public static Result ofGutter() {
        return new Result(Status.GUTTER, NO_COUNT);
    }

    public static Result ofStrike() {
        return new Result(Status.STRIKE, STRIKE_COUNT);
    }

    public static Result ofMiss() {
        return new Result(Status.MISS, NO_COUNT);
    }

    public static Result ofMiss(int value) {
        return new Result(Status.MISS, value);
    }

    public static Result ofSpare() {
        return new Result(Status.SPARE, SPARE_COUNT);
    }

    public static Result of(int stage, int step, int hitCount, boolean clear) {
        if (hitCount == NO_COUNT) {
            return Result.ofGutter();
        }

        if (stage == Frames.BONUS_NUMBER && step == Frames.BONUS_TRY_COUNT && hitCount == STRIKE_COUNT) {
            return Result.ofStrike();
        }

        if (step == Frames.BEGIN_NUMBER && clear) {
            return Result.ofStrike();
        }

        if (clear) {
            return Result.ofSpare();
        }

        return Result.ofMiss(hitCount);
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result that = (Result) o;
        return count == that.count &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, count);
    }

    @Override
    public String toString() {
        if (status.equals(Status.MISS)) {
            return String.valueOf(count);
        }

        return String.valueOf(status);
    }
}
