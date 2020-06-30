package bowling.domain.frame;

import java.util.Objects;

public class Point {

    private static final int MIN_POINT = 0;

    private final int point;

    private Point(int point) {
        validatePoint(point);
        this.point = point;
    }

    public static Point inputPoint(int point) {
        return new Point(point);
    }

    private void validatePoint(int point) {
        if (point < MIN_POINT) {
            throw new IllegalArgumentException("포인트는 0보다 작을 수 없습니다.");
        }
    }

    public int getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point1 = (Point) o;
        return point == point1.point;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
    public String toString() {
        return String.valueOf(point);
    }
}
