package bowling.domain.frame;

import java.util.Objects;

public class Point {

    private static final int MIN_POINT = 0;
    private static final int MAX_POINT = 10;
    private static final String INVALID_POINT = "넘어진 핀 수는 0 이상 10 이하의 자연수입니다. 입력 된 수 : %d";

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
            throw new IllegalArgumentException(String.format(INVALID_POINT, point));
        }

        if (point > MAX_POINT) {
            throw new IllegalArgumentException(String.format(INVALID_POINT, point));
        }
    }

    public int getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
