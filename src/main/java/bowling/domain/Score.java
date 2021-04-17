package bowling.domain;


public class Score {

    private final Point firstPoint;
    private final Point secondPoint;

    private static final int POINT_MAX_BOUND = 10;
    private static final int POINT_MIN_BOUND = 0;

    private Score(Point firstPoint, Point secondPoint) {
        valid(firstPoint, secondPoint);
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    private void valid(Point firstPoint, Point secondPoint) {
        if (firstPoint.toInt() + secondPoint.toInt() > POINT_MAX_BOUND) {
            throw new IllegalArgumentException("두 스코어의 합은 10을 넘을 수 없습니다.");
        }
    }

    public static Score of(Point firstPoint, Point secondPoint) {
        return new Score(firstPoint, secondPoint);
    }

    public static Score first(Point firstPoint) {
        return new Score(firstPoint, Point.of(POINT_MIN_BOUND));
    }

    public Score next(Point secondPoint) {
        return new Score(this.firstPoint, secondPoint);
    }

    public BowlingRole type() {
        return BowlingRole.valueOf(this);
    }

    public int firstPoint() {
        return firstPoint.toInt();
    }

    public int secondPoint() {
        return secondPoint.toInt();
    }

    public boolean isStrike() {
        return firstPoint() == 10;
    }

    public int total() {
        return firstPoint() + secondPoint();
    }
}
