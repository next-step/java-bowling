package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Points {

    private List<Point> points;

    private final int FIRST_INDEX = 0;

    public Points() {
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public int findFirstPoint() {
        return points.get(FIRST_INDEX).currentPoint();
    }

    public int currentPoint() {
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

    public boolean canPlayBonusGame() {
        int pointSum = points.stream()
                .mapToInt(Point::currentPoint)
                .sum();

        int bowlCount = points.size();

        return pointSum == 10 && bowlCount <= 2;
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
