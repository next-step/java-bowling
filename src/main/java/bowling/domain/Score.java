package bowling.domain;

import java.util.Objects;

public class Score {

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
        return of(10 - point, 1);
    }

    public Score second(int point) {
        return of(this.point - point, count - 1);
    }

    private void validatePoint(int point, int count) {
        if (point > 10 || point < 0) {
            throw new IllegalArgumentException();
        }

        if (count < 0) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isSpare() {
        return point == 0 && count == 0;
    }

    public boolean isStrike() {
        return point == 0 && count == 1;
    }

    public boolean isMiss() {
        return point != 0 && count == 0;
    }

    public boolean isGutter() {
        return point == 10;
    }

    public int getPoint() {
        return this.point;
    }

    public boolean isCount() {
        return count != 0;
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
