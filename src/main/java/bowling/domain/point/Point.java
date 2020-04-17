package bowling.domain.point;

import java.util.Objects;

public class Point {
    private static final int MAX_POINT = 10;
    private static final int MIN_POINT = 0;

    private final int point;

    public Point(int point) {
        validate(point);
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public boolean isScoreable(int leftPin) {
        return this.point <= leftPin;
    }

    private void validate(int input) {
        if (input < MIN_POINT || input > MAX_POINT) {
            throw new PointOutOfRangeException(String.format("포인트는 %s ~ %s 사이만 가능합니다.", MIN_POINT, MAX_POINT));
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
    public int hashCode() {
        return Objects.hash(point);
    }
}
