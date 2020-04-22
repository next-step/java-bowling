package bowling.domain.status.running;

import bowling.domain.point.Point;

public class Cover extends Running{

    private final Point firstPoint;

    public static Cover of(Point point) {
        return new Cover(point);
    }

    private Cover(Point firstPoint) {
        this.firstPoint = firstPoint;
    }
}
