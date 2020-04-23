package bowling.domain.status.finished;

import bowling.domain.point.Point;
import bowling.domain.score.Score;

public class Spare extends Finished {
    private final Point firstPoint;

    public static Spare of(Point firstPoint) {
        return new Spare(firstPoint);
    }

    private Spare(Point firstPoint) {
        this.firstPoint = firstPoint;
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
