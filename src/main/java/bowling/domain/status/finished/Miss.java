package bowling.domain.status.finished;

import bowling.domain.point.Point;
import bowling.domain.score.Score;

public class Miss extends Finished {
    private final Point firstPoint;
    private final Point secondPoint;

    public static Miss of(Point firstPoint, Point secondPoint) {
        return new Miss(firstPoint, secondPoint);
    }

    private Miss(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    public String print() {
        return firstPoint.print() + "|" + secondPoint.print();
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(firstPoint.add(secondPoint));
    }
}
