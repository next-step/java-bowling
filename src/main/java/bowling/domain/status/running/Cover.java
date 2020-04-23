package bowling.domain.status.running;

import bowling.domain.frame.OverThrowBallException;
import bowling.domain.point.Point;
import bowling.domain.score.Score;
import bowling.domain.status.Status;
import bowling.domain.status.finished.Double;
import bowling.domain.status.finished.Gutter;
import bowling.domain.status.finished.Miss;
import bowling.domain.status.finished.Spare;

public class Cover extends Running {
    private final Point firstPoint;

    public static Cover of(Point point) {
        return new Cover(point);
    }

    private Cover(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    @Override
    public Status throwBall(Point point) {
        validate(point);
        int sumPoint = firstPoint.add(point);
        if (firstPoint.isMaxPoint() && point.isMaxPoint()) {
            return Double.of();
        }
        if (sumPoint == Point.MAX_POINT) {
            return Spare.of(firstPoint);
        }
        if (sumPoint == Point.MIN_POINT) {
            return Gutter.of();
        }
        return Miss.of(firstPoint, point);
    }

    @Override
    public String print() {
        return firstPoint.print();
    }

    @Override
    public Score getScore() {
        return null;
    }

    private void validate(Point point) {
        Point leftPoint = firstPoint.getLeftPoint();
        if (firstPoint.isOver(point)) {
            throw new OverThrowBallException("다시 입력해주세요(남은 핀: " + leftPoint.getPoint() + ")");
        }
    }
}
