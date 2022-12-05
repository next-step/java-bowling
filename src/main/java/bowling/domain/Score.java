package bowling.domain;

import java.util.Objects;

public class Score {
    private final Point point;
    private final int left;

    public Score(Point point, int left) {
        this.point = point;
        this.left = left;
    }

    public static Score ofStrike(Point point) {
        return new Score(point, 2);
    }

    public static Score ofSpare(Point point) {
        return new Score(point, 1);
    }

    public static Score ofMiss(Point point) {
        return new Score(point, 0);
    }

    public boolean canReceiveExtraPoint() {
        return left > 0;
    }

    public Score addExtraPoint(Point extraPoint) {
        if (left == 0) {
            return this;
        }
        return new Score(point.add(extraPoint), left - 1);
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return left == score.left && Objects.equals(point, score.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, left);
    }

    @Override
    public String toString() {
        return "Score{" +
                "point=" + point +
                ", left=" + left +
                '}';
    }
}
