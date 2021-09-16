package bowling.model;

import java.util.Objects;

public class Point {
    private static final int MAX_POINT = 10;
    private static final int MIN_POINT = 0;

    private final int point;

    public Point(int point) {
        isValidRange(point);
        this.point = point;
    }

    public static void isValidRange(int pinCount) {
        if(pinCount < MIN_POINT || pinCount > MAX_POINT) {
            throw new IllegalArgumentException("핀 갯수를 1개 이상 10개 이하로 입력해주세요.");
        }
    }

    public Point add(int point) {
        return new Point(this.point + point);
    }

    public boolean isStrike() {
        if (this.point == MAX_POINT) {
            return true;
        }

        return false;
    }

    public boolean isGutter() {
        if (this.point == MIN_POINT) {
            return true;
        }

        return false;
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
