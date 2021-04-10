package bowling.domain;


public class Score {

    private final Point firstPoint;
    private final Point secondPoint;

    private Score(Point firstPoint, Point secondPoint) {
        vaild(firstPoint, secondPoint);
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    private void vaild(Point firstPoint, Point secondPoint) {
        int total = firstPoint.toInt() + secondPoint.toInt();
        if (total > 10) {
            throw new IllegalArgumentException("두 스코어의 합은 10을 넘을 수 없습니다.");
        }
    }

    public static Score of(Point firstPoint, Point secondPoint) {
        return new Score(firstPoint, secondPoint);
    }

    public static Score first(Point firstPoint) {
        return new Score(firstPoint, Point.of(0));
    }

    public Score next(Point secondPoint) {
        return new Score(this.firstPoint, secondPoint);
    }

    public BowlingRole score() {
        return BowlingRole.valueOf(firstPoint.toInt(), secondPoint.toInt());
    }
}
