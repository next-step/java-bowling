package bowling.domain.point;

public class Point {

    private static final int MIN_POINT = 0;
    private static final int MAX_POINT = 10;

    private int point;

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

        if (point > MAX_POINT) {
            throw new IllegalArgumentException("포인트는 10보다 클 수 없습니다.");
        }
    }

    public int getPoint() {
        return point;
    }
}
