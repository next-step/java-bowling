package bowling.domain.point;

import bowling.exception.ScorePointRangeOutBoundException;

import java.util.Objects;

public class Point {


    public static final int MIN_SCORE_COUNT = 0;
    public static final int MAX_SCORE_COUNT = 10;


    private final int point;

    private Point(int point) {
        this.point = point;
    }


    public static Point valueOf(int point) {
        validPoint(point);
        return new Point(point);
    }

    public static Point valueOfString(String pitchPoint) {
        return valueOf(Integer.parseInt(pitchPoint));
    }

    private static void validPoint(int point) {
        if (point < MIN_SCORE_COUNT || point > MAX_SCORE_COUNT) {
            throw new ScorePointRangeOutBoundException();
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
