package bowling.domain.status.running;

import bowling.domain.frame.OverThrowBallException;
import bowling.domain.point.Point;
import bowling.domain.score.NoneScoreException;
import bowling.domain.score.Score;
import bowling.domain.status.Status;
import bowling.domain.status.finished.BonusEnd;
import bowling.domain.status.finished.Spare;
import bowling.domain.status.finished.Strike;

public class Bonus extends Running {
    private final Point firstPoint;
    private final Point secondPoint;

    public static Bonus of(Point firstPoint, Point secondPoint) {
        return new Bonus(firstPoint, secondPoint);
    }

    private Bonus(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    public Status throwBall(Point thirdPoint) {
        validate(thirdPoint);
        return BonusEnd.of(this, thirdPoint);
    }

    @Override
    public String print() {
        if (isSpare()) {
            return Spare.of(firstPoint).print();
        }
        return Strike.of().print() + "|" + secondPoint.print();
    }

    @Override
    public Score getScore() {
        if (isSpare()) {
            return Score.ofSpare();
        }
        throw new NoneScoreException("스코어가 없습니다");
    }

    private void validate(Point point) {
        Point leftPoint = secondPoint.getLeftPoint();
        if (!secondPoint.isMaxPoint() && !isSpare() && secondPoint.isOver(point)) {
            throw new OverThrowBallException("다시 입력해주세요(남은 핀: " + leftPoint.getPoint() + ")");
        }
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public boolean isSpare() {
        return !firstPoint.isMaxPoint() && firstPoint.add(secondPoint) == Point.MAX_POINT;
    }
}
