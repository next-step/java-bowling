package bowling.domain.point;

public class Point {
    private static final int MIN_POINT = 0;
    private static final int MAX_POINT = 10;

    private final int point;

    public Point(int point) {
        this.validatePoint(point);
        this.point = point;
    }

    public void validatePoint(int point) {
        if (point > MAX_POINT) {
            throw new IllegalArgumentException("point less than 10");
        }

        if (point < MIN_POINT) {
            throw new IllegalArgumentException("point bigger than 0");
        }
    }

    public int getPoint() {
        return point;
    }
}
