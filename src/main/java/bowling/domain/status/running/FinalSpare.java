package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.score.Score;
import bowling.domain.status.Status;
import bowling.domain.status.finished.FinalSpareEnd;
import bowling.domain.status.finished.Gutter;
import bowling.domain.status.finished.Miss;
import bowling.domain.status.finished.Spare;

public class FinalSpare extends Running {
    private final Point firstPoint;
    private final Point secondPoint;

    public static FinalSpare of(Point firstPoint, Point secondPoint) {
        return new FinalSpare(firstPoint, secondPoint);
    }

    private FinalSpare(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    public Status throwBall(Point thirdPoint) {
        return FinalSpareEnd.of(Spare.of(firstPoint), thirdPoint);
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
