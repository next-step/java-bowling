package bowling.domain.status.finished;

import bowling.domain.point.Point;
import bowling.domain.score.Score;

public class FinalSpareEnd extends Finished{
    private final Spare spare;
    private final Point thirdPoint;

    public static FinalSpareEnd of(Spare spare, Point thirdPoint) {
        return new FinalSpareEnd(spare, thirdPoint);
    }

    private FinalSpareEnd(Spare spare, Point thirdPoint) {
        this.spare = spare;
        this.thirdPoint = thirdPoint;
    }

    @Override
    public String print() {
        return spare.print() + "|" + thirdPoint.print();
    }

    @Override
    public Score getScore() {
        return null;
    }
}
