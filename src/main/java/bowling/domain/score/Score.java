package bowling.domain.score;


import bowling.domain.BowlingRole;

import java.util.Arrays;
import java.util.List;

public class Score {

    private static final int POINT_MAX_BOUND = 10;
    private static final int POINT_MIN_BOUND = 0;
    private static final int EMPTY_POINT = -1;

    private final List<Point> pointList;

    private Score(Point firstPoint, Point secondPoint) {
        valid(firstPoint, secondPoint);
        this.pointList = Arrays.asList(firstPoint, secondPoint);
    }

    private void valid(Point firstPoint, Point secondPoint) {
        if (firstPoint.toInt() + secondPoint.toInt() > POINT_MAX_BOUND) {
            throw new IllegalArgumentException("두 스코어의 합은 10을 넘을 수 없습니다.");
        }
    }

    public static Score of(Point firstPoint, Point secondPoint) {
        return new Score(firstPoint, secondPoint);
    }

    public static Score initScore() {
        return new Score(Point.of(), Point.of(POINT_MIN_BOUND));
    }

    public static Score first(Point firstPoint) {
        return new Score(firstPoint, Point.of(POINT_MIN_BOUND));
    }

    public Score next(Point secondPoint) {
        return new Score(pointList.get(0), secondPoint);
    }

    public BowlingRole type() {
        return BowlingRole.valueOf(this);
    }

    public int firstPoint() {
        return pointList.get(0).toInt();
    }

    public int secondPoint() {
        return pointList.get(1).toInt();
    }

    public boolean isStrike() {
        return firstPoint() == POINT_MAX_BOUND;
    }

    public int currentPoint() {
        if (firstPoint() + secondPoint() == EMPTY_POINT) {
            return POINT_MIN_BOUND;
        }
        return total();
    }

    public int total() {
        return pointList.stream()
                .map(Point::toInt)
                .reduce(Integer::sum)
                .orElse(POINT_MIN_BOUND);
    }
}
