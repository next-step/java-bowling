package bowling.domain.point;

import java.util.Objects;

public class Point {
    private static final int MIN_POINT = 0;
    private static final int MAX_POINT = 10;
    public static final String MAX_POINT_ERROR = "포인트는 10보다 클 수 없습니다";
    public static final String MIN_POINT_ERROR = "포인트는 0보다 작을 수 없습니다";

    private int point;

    private Point(int point) {
        validatePoint(point);
        this.point = point;
    }

    public static Point of(int point) {
        return new Point(point);
    }

    private void validatePoint(int point) {
        if (point < MIN_POINT) {
            throw new IllegalArgumentException(MIN_POINT_ERROR);
        }
        if (point > MAX_POINT) {
            throw new IllegalArgumentException(MAX_POINT_ERROR);
        }
    }

    public int getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return String.valueOf(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point1 = (Point) o;
        return getPoint() == point1.getPoint();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoint());
    }
}
