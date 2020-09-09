package bowling.domain;

import java.util.Objects;

public class GameResult {
    public static final int STRIKE_COUNT = 10;
    public static final int SPARE_COUNT = STRIKE_COUNT;
    public static final int NO_COUNT = 0;

    private Result result;
    private int value;

    GameResult(Result result, int value) {
        this.result = result;
        this.value = value;
    }

    public static GameResult ofGutter() {
        return new GameResult(Result.GUTTER, NO_COUNT);
    }

    public static GameResult ofStrike() {
        return new GameResult(Result.STRIKE, STRIKE_COUNT);
    }

    public static GameResult ofMiss() {
        return new GameResult(Result.MISS, NO_COUNT);
    }

    public static GameResult ofMiss(int value) {
        return new GameResult(Result.MISS, value);
    }

    public static GameResult ofSpare() {
        return new GameResult(Result.SPARE, SPARE_COUNT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return value == that.value &&
                result == that.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, value);
    }

    @Override
    public String toString() {
        if (result.equals(Result.MISS)) {
            return String.valueOf(value);
        }

        return String.valueOf(result);
    }
}
