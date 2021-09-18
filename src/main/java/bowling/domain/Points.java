package bowling.domain;

import bowling.view.ScoreType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Points {

    private final List<Point> points;

    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    public Points() {
        points = new ArrayList<>();
    }

    public void addPoint(int point) {
        points.add(new Point(point, findScoreType(point)));
    }

    public void addBonusPoint(int point) {
        points.add(new Point(point, true, findBonusScoreType(point)));
    }

    private ScoreType findBonusScoreType(int point) {
        if (point == Point.MAX_POINT) {
            return ScoreType.STRIKE;
        }
        if (point == Point.MIN_POINT) {
            return ScoreType.GUTTER;
        }
        return ScoreType.MISS;
    }

    private ScoreType findScoreType(int point) {
        if (point == Point.MAX_POINT && points.size() == FIRST_INDEX) {
            return ScoreType.STRIKE;
        }
        if (currentPointSum() + point == Point.MAX_POINT && bowlCount() > FIRST_INDEX) {
            return ScoreType.SPARE;
        }
        if (point == Point.MIN_POINT) {
            return ScoreType.GUTTER;
        }
        return ScoreType.MISS;
    }

    public int findFirstPointScore() {
        return points.get(FIRST_INDEX).currentPoint();
    }


    public Point findFirstPoint() {
        return points.get(FIRST_INDEX);
    }

    public Point
    findSecondPoint() {
        return points.get(SECOND_INDEX);
    }


    public Optional<Point> findBonusPoint() {
        return points.stream()
                .filter(point -> point.isBonusPoint())
                .findFirst();
    }

    public int currentPointSum() {
        return points.stream()
                .mapToInt(Point::currentPoint)
                .sum();
    }

    public List<Point> values() {
        return points;
    }

    public int bowlCount() {
        return points.size();
    }

    public boolean isNormalFrameEnd() {
        return bowlCount() == NormalFrame.MAX_TRY;
    }

    public boolean isFinalFrameEnd() {
        return bowlCount() == FinalFrame.MAX_TRY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Points points1 = (Points) o;
        return FIRST_INDEX == points1.FIRST_INDEX && Objects.equals(points, points1.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, FIRST_INDEX);
    }
}
