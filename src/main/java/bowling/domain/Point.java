package bowling.domain;

public class Point {

    private final int point;

    private Point(int point) {
        valid(point);
        this.point = point;
    }

    private void valid(int point) {
        if (point < 0 || point > 10) {
            throw new IllegalArgumentException("포인트는 0에서 10 사이여야 합니다.");
        }
    }

    public static Point of(int point) {
        return new Point(point);
    }

    public int toInt() {
        return point;
    }
}
