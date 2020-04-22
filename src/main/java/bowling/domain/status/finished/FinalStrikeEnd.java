package bowling.domain.status.finished;

import bowling.domain.point.Point;
import bowling.domain.status.running.FinalReady;

public class FinalStrikeEnd extends Finished{
    private final Point secondPoint;
    private final Point thirdPoint;

    public static FinalStrikeEnd of(Point secondPoint, Point thirdPoint) {
        return new FinalStrikeEnd(secondPoint, thirdPoint);
    }

    private FinalStrikeEnd(Point secondPoint, Point thirdPoint) {
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
    }

    @Override
    public String print() {
        return "X|" + FinalReady.of().throwBall(secondPoint).throwBall(thirdPoint).print();
    }
}
