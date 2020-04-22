package bowling.domain.status.finished;

import bowling.domain.point.Point;
import bowling.domain.score.Score;

public class Spare extends Finished {
    private final Point firstPoint;
    private final Point secondPoint;

    public static Spare of(Point firstPoint, Point secondPoint) {
        return new Spare(firstPoint, secondPoint);
    }

    private Spare(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    public String print() {
        return firstPoint.print() + "|/";
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }
}
