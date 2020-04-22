package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.Status;
import bowling.domain.status.finished.Gutter;
import bowling.domain.status.finished.Miss;
import bowling.domain.status.finished.Spare;

public class Cover extends Running{
    private final Point firstPoint;

    public static Cover of(Point point) {
        return new Cover(point);
    }

    private Cover(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    @Override
    public Status throwBall(Point point) {
        Point sumPoint = firstPoint.add(point);
        if (sumPoint.isMaxPoint()) {
            return Spare.of(firstPoint, point);
        }
        if(sumPoint.isMinPoint()) {
            return Gutter.of();
        }
        return Miss.of(firstPoint, point);
    }
}
