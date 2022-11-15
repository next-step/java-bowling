package bowling.domain;

import java.util.Objects;

public class Point {
    public static final int MIN_POINT_COUNT = 0;
    public static final int MAX_POINT_COUNT = 10;
    public static final Point All_KNOCK_DOWN_POINT = Point.of(MAX_POINT_COUNT);
    public static final Point NO_KNOCK_DOWN_POINT = Point.of(MIN_POINT_COUNT);

    private final int point;

    private Point(int point) {
        if (point < MIN_POINT_COUNT) {
            throw new IllegalArgumentException("쓰러트린 핀의 수가 유효범위에 들지 않았습니다.");
        }
        this.point = point;
    }

    public static Point of(int knockDownCount) {
        return new Point(knockDownCount);
    }

    public Point add(Point point) {
        return Point.of(this.point + point.point);
    }

    public boolean areAllPinsDown() {
        return point >= MAX_POINT_COUNT;
    }

    public boolean isSmallerThan(Point point) {
        return this.point < point.point;
    }

    public int point() {
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
