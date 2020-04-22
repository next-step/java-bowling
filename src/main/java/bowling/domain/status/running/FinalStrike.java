package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.Status;
import bowling.domain.status.finished.FinalStrikeEnd;

public class FinalStrike extends Running {
    private final Point secondPoint;

    public static FinalStrike of(Point secondPoint) {
        return new FinalStrike(secondPoint);
    }

    private FinalStrike(Point secondPoint) {
        this.secondPoint = secondPoint;
    }

    @Override
    public Status throwBall(Point thirdPoint) {
        return FinalStrikeEnd.of(secondPoint, thirdPoint);
    }

    @Override
    public String print() {
        return "X|" + secondPoint.print();
    }
}
