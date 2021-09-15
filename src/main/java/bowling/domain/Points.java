package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Points {

    private List<Point> points;

    private final int FIRST_INDEX = 0;
    private final int SECOND_INDEX = 1;
    private final int BONUS_INDEX = 2;

    public Points() {
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public int findFirstPoint() {
        return points.get(FIRST_INDEX).currentPoint();
    }

    public int findSecondPoint() {
        return points.get(SECOND_INDEX).currentPoint();
    }

    public int findBonusPoint() {
        return points.get(BONUS_INDEX).currentPoint();
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
        if (findFirstPoint() == 10 && points.size() == 2) {
            return false;
        }

        if (points.stream()
                .mapToInt(Point::currentPoint)
                .sum() == 10 && points.size() == 3) {
            return false;
        }

        return true;
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
