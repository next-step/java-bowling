package bowling.domain;

import java.util.Objects;

public class GameResult {
    public static final int STRIKE_COUNT = 10;
    public static final int SPARE_COUNT = STRIKE_COUNT;
    public static final int NO_COUNT = 0;

    private Status status;
    private int count;

    GameResult(Status status, int count) {
        this.status = status;
        this.count = count;
    }

    public static GameResult ofGutter() {
        return new GameResult(Status.GUTTER, NO_COUNT);
    }

    public static GameResult ofStrike() {
        return new GameResult(Status.STRIKE, STRIKE_COUNT);
    }

    public static GameResult ofMiss() {
        return new GameResult(Status.MISS, NO_COUNT);
    }

    public static GameResult ofMiss(int value) {
        return new GameResult(Status.MISS, value);
    }

    public static GameResult ofSpare() {
        return new GameResult(Status.SPARE, SPARE_COUNT);
    }

    public static GameResult toResult(int stage, int step, int hitCount, boolean clear) {
        if (hitCount == NO_COUNT) {
            return GameResult.ofGutter();
        }

        if (stage == Frame.BONUS_STAGE && step == Frame.BONUS_STEP && clear) {
            return GameResult.ofStrike();
        }

        if (step == Frame.FIRST_STEP && clear) {
            return GameResult.ofStrike();
        }

        if (clear) {
            return GameResult.ofSpare();
        }

        return GameResult.ofMiss(hitCount);
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
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
