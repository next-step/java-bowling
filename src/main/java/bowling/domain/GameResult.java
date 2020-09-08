package bowling.domain;

import java.util.Objects;

public class GameResult {
    private static final int ALL_COUNT = 10;
    private static final int NO_COUNT = 0;

    private Result result;
    private int value;

    private GameResult(Result result, int value) {
        this.result = result;
        this.value = value;
    }

    public static GameResult ofGutter() {
        return new GameResult(Result.GUTTER, NO_COUNT);
    }

    public static GameResult ofStrike() {
        return new GameResult(Result.STRIKE, ALL_COUNT);
    }

    public static GameResult ofMiss() {
        return new GameResult(Result.MISS, NO_COUNT);
    }

    public static GameResult ofMiss(int value) {
        return new GameResult(Result.MISS, value);
    }

    public static GameResult ofSpare() {
        return new GameResult(Result.SPARE, ALL_COUNT);
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
