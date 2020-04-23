package bowling.domain.status.finished;

import bowling.domain.point.Point;
import bowling.domain.score.Score;
import bowling.domain.status.running.Bonus;

public class BonusEnd extends Finished {
    private final Bonus bonus;
    private final Point thirdPoint;

    public static BonusEnd of(Bonus bonus, Point thirdPoint) {
        return new BonusEnd(bonus, thirdPoint);
    }

    public BonusEnd(Bonus bonus, Point thirdPoint) {
        this.bonus = bonus;
        this.thirdPoint = thirdPoint;
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder(bonus.print());
        sb.append("|");
        int sumPoint = bonus.getSecondPoint().add(thirdPoint);
        if (!bonus.isSpare() && sumPoint == Point.MAX_POINT) {
            return sb.append("/").toString();
        }
        return sb.append(thirdPoint.print()).toString();
    }

    @Override
    public Score getScore() {
        return null;
    }
}
