package bowling.domain.point;

import java.util.Objects;

public class Point {
    private static final int STRIKE_POINT = 10;
    private static final int ZERO_POINT = 0;

    private final int point;

    public Point(int point) {
        validatePoint(point);

        this.point = point;
    }

    private void validatePoint(int point) {
        if (point > STRIKE_POINT) {
            throw new IllegalArgumentException("점수는 10점을 넘을수 없습니다.");
        }

        if (point < ZERO_POINT) {
            throw new IllegalArgumentException("점수는 0점이상이어야 합니다.");
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
}
