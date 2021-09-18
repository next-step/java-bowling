package bowling.domain;

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

    public void addPoint(Point point) {
        points.add(point);
    }

    public int findFirstPointScore() {
        return points.get(FIRST_INDEX).currentPoint();
    }

    public int findSecondPointScore() {
        return points.get(SECOND_INDEX).currentPoint();
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
