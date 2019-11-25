package bowling.domain;

import java.util.Objects;

public class Score {

    private static final int MAX_POINT = 10;
    private static final int MIN_POINT = 0;
    private static final int MAX_COUNT = 1;
    private static final int MIN_COUNT = 0;
    private static final int INDEX = 1;

    private int point;
    private int count;

    private Score(int point, int count) {
        validatePoint(point, count);
        this.point = point;
        this.count = count;
    }

    public static Score of(int point, int count) {
        return new Score(point, count);
    }

    public static Score first(int point) {
        return of(point, MAX_COUNT);
    }

    public Score second(int point) {
        return of(point, count - INDEX);
    }

    private void validatePoint(int point, int count) {
        if (point > MAX_POINT || point < MIN_POINT) {
            throw new IllegalArgumentException();
        }

        if (count < MIN_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isSpare() {
        return point == MIN_POINT && count == MIN_COUNT;
    }

    public boolean isStrike() {
        return point == MAX_POINT && count == MAX_COUNT;
    }

    public boolean isMiss() {
        return point != MIN_POINT && count == MIN_COUNT;
    }

    public boolean isGutter() {
        return point == MIN_POINT;
    }

    public int getPoint() {
        return this.point;
    }

    public boolean isCount() {
        return count != MIN_COUNT;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return point == score.point &&
                count == score.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, count);
    }
}
