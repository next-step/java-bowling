package bowling.domain;

import java.util.Objects;

public class Point {
    private final int point;

    private Point(int point) {
        this.point = point;
    }

    public static Point of(int point) {
        return new Point(point);
    }

    public Point add(Point other) {
        return Point.of(point + other.point);
    }

    public boolean isFull() {
        return point >= Pin.FULL_POINT;
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
        return "Point{" +
                "point=" + point +
                '}';
    }

}
