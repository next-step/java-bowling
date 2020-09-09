package bowling.domain;

import java.util.Objects;

public class GameResult {
    public static final int STRIKE_COUNT = 10;
    public static final int SPARE_COUNT = STRIKE_COUNT;
    public static final int NO_COUNT = 0;

    private Status status;
    private int value;

    GameResult(Status status, int value) {
        this.status = status;
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return value == that.value &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, value);
    }

    @Override
    public String toString() {
        if (status.equals(Status.MISS)) {
            return String.valueOf(value);
        }

        return String.valueOf(status);
    }
}
