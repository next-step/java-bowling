package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.Status;
import bowling.domain.status.finished.Strike;

public class FinalReady extends Running {

    public static FinalReady of() {
        return new FinalReady();
    }

    @Override
    public Status throwBall(Point point) {
        return FinalCover.of(point);
    }

    @Override
    public String print() {
        return "";
    }
}
