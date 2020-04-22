package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.Status;
import bowling.domain.status.finished.Strike;

public class Ready extends Running{

    public static Ready of() {
        return new Ready();
    }

    @Override
    public Status throwBall(Point point) {
        if (point.isMaxPoint()) {
            return Strike.of();
        }
        return Cover.of(point);
    }
}
