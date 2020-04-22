package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.Status;
import bowling.domain.status.finished.Gutter;
import bowling.domain.status.finished.Miss;
import bowling.domain.status.finished.Spare;

public class FinalCover extends Running {
    private final Point firstPoint;

    public static FinalCover of(Point point) {
        return new FinalCover(point);
    }

    private FinalCover(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    @Override
    public Status throwBall(Point secondPoint) {
        int sumPoint = firstPoint.add(secondPoint);
        if (firstPoint.isMaxPoint()) {
            return FinalStrike.of(secondPoint);
        }
        if (sumPoint == Point.MAX_POINT) {
            return FinalSpare.of(firstPoint, secondPoint);
        }
        if (sumPoint == Point.MIN_POINT) {
            return Gutter.of();
        }
        return Miss.of(firstPoint, secondPoint);
    }

    @Override
    public String print() {
        return firstPoint.print();
    }
}
