package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.score.Score;
import bowling.domain.status.Status;
import bowling.domain.status.finished.Double;
import bowling.domain.status.finished.Gutter;
import bowling.domain.status.finished.Miss;
import bowling.domain.status.finished.Spare;

public class Cover extends Running {
    private final Point firstPoint;

    public static Cover of(Point point) {
        return new Cover(point);
    }

    private Cover(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    @Override
    public Status throwBall(Point point) {
        int sumPoint = firstPoint.add(point);
        if (firstPoint.isMaxPoint() && point.isMaxPoint()) {
            return Double.of();
        }
        if (sumPoint == Point.MAX_POINT) {
            return Spare.of(firstPoint, point);
        }
        if (sumPoint == Point.MIN_POINT) {
            return Gutter.of();
        }
        return Miss.of(firstPoint, point);
    }

    @Override
    public String print() {
        return firstPoint.print();
    }

    @Override
    public Score getScore() {
        return null;
    }
}
