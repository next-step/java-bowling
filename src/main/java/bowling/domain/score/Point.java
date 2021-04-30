package bowling.domain.score;

public class Point {

    private final int point;

    private static final int POINT_MAX_BOUND = 10;
    private static final int POINT_MIN_BOUND = 0;
    private static final int EMPTY = -1;

    private Point(int point) {
        valid(point);
        this.point = point;
    }

    private Point() {
        this.point = EMPTY;
    }

    private void valid(int point) {
        if (point < POINT_MIN_BOUND || point > POINT_MAX_BOUND) {
            throw new IllegalArgumentException("포인트는 0에서 10 사이여야 합니다.");
        }
    }

    public static Point of(int point) {
        return new Point(point);
    }

    public static Point of() {
        return new Point();
    }


    public int toInt() {
        return point;
    }
}
