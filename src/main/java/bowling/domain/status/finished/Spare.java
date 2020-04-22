package bowling.domain.status.finished;

import bowling.domain.point.Point;

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
}
