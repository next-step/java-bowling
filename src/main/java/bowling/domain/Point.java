package bowling.domain;

import bowling.view.ScoreType;

import java.util.Objects;

public class Point {

    public static final int FINAL_MAX_POINT = 20;
    public static final int MAX_POINT = 10;
    public static final int MIN_POINT = 0;

    private int point;
    private boolean isBonusPoint;
    private final ScoreType scoreType;

    public Point(int point, ScoreType scoreType) {
        this(point, false, scoreType);
    }

    public Point(int point, boolean isBonusPoint, ScoreType scoreType) {
        validate(point);
        this.scoreType = scoreType;
        this.point = point;
        this.isBonusPoint = isBonusPoint;
    }

    public int currentPoint() {
        return point;
    }

    public boolean isBonusPoint() {
        return isBonusPoint;
    }

    public String findPointResult() {
        if (scoreType.equals(ScoreType.MISS)) {
            return String.valueOf(point);

        }
        return scoreType.value();
    }

    public ScoreType findScoreType() {
        return scoreType;
    }

    private void validate(int point) {
        if (point > MAX_POINT || point < MIN_POINT) {
            throw new IllegalArgumentException(MIN_POINT + " ~ " + MAX_POINT + "까지 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point1 = (Point) o;
        return point == point1.point;
    }

    @Override
    public String toString() {
        return String.valueOf(point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
