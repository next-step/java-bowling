package bowling.domain.status.finished;

import bowling.domain.point.Point;
import bowling.domain.score.Score;
import bowling.domain.status.running.Bonus;

public class BonusEnd extends Finished {
    private final Point firstPoint;
    private final Point secondPoint;
    private final Point thirdPoint;

    public static BonusEnd of(Point firstPoint, Point secondPoint, Point thirdPoint) {
        return new BonusEnd(firstPoint, secondPoint, thirdPoint);
    }

    public BonusEnd(Point firstPoint, Point secondPoint, Point thirdPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder(Bonus.of(firstPoint, secondPoint).print());
        sb.append("|");
        if (isSpare()) {
            return sb.append("/").toString();
        }
        return sb.append(thirdPoint.print()).toString();
    }

    @Override
    public Score getScore() {
        return null;
    }

    private boolean isSpare() {
        return secondPoint.add(thirdPoint) == Point.MAX_POINT && !firstPoint.isMaxPoint();
    }
}
