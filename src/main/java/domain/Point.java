package domain;

public class Point {
    private int point;

    public Point(int point) {
        this.point = point;
    }

    private static boolean isEnablePoint(int point) {
        return NormalScore.ZERO <= point && point <= NormalScore.STRIKE;
    }

    public static Point bowl(int point) {
        if (isEnablePoint(point)) {
            return new Point(point);
        }
        throw new IllegalArgumentException("점수는 0에서 10이내만 입력 할 수 있습니다.");
    }

    public int getPoint() {
        return point;
    }
}
