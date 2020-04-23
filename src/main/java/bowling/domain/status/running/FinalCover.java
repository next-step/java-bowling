package bowling.domain.status.running;

import bowling.domain.frame.OverThrowBallException;
import bowling.domain.point.Point;
import bowling.domain.score.Score;
import bowling.domain.status.Status;
import bowling.domain.status.finished.Gutter;
import bowling.domain.status.finished.Miss;

public class FinalCover extends Running {
    private final Point firstPoint;

    public static FinalCover of(Point point) {
        return new FinalCover(point);
    }

    private FinalCover(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    @Override
    public Status throwBall(Point secondPoint) {
        validate(secondPoint);
        int sumPoint = firstPoint.add(secondPoint);
        if (firstPoint.isMaxPoint() || sumPoint == Point.MAX_POINT) {
            return Bonus.of(firstPoint, secondPoint);
        }
        if (sumPoint == Point.MIN_POINT) {
            return Gutter.of();
        }
        return Miss.of(firstPoint, secondPoint);
    }

    @Override
    public String print() {
        return firstPoint.print();
    }

    @Override
    public Score getScore() {
        if (firstPoint.isMaxPoint()) {
            return Score.ofStrike();
        }
        return null;
    }

    private void validate(Point point) {
        Point leftPoint = firstPoint.getLeftPoint();
        if (!firstPoint.isMaxPoint() && firstPoint.isOver(point)) {
            throw new OverThrowBallException("다시 입력해주세요(남은 핀: " + leftPoint.getPoint() + ")");
        }
    }
}
