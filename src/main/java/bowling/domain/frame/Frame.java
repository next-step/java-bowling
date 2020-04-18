package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.point.PointOutOfRangeException;
import bowling.domain.point.Points;
import bowling.score.Score;

import java.util.Objects;

public abstract class Frame {
    protected Points points;

    public Frame() {
        points = new Points();
    }

    abstract public boolean isThrowable();

    public Frame throwBall(int fallenCount) throws PointOutOfRangeException {
        int leftPoint = points.getLeftPoint();
        Point point = Point.of(fallenCount);
        if (point.isScoreable(leftPoint)) {
            this.points.addPoint(point);
            return this;
        }
        throw new OverThrowBallException("다시 입력해주세요(남은 핀: " + leftPoint + ")");
    }

    public Points getPoints() {
        return this.points;
    }

    public String getScoreMark() {
        return Score.getScoreMark(this);
    }

    public boolean isLast() {
        if (this instanceof FinalFrame && !isThrowable()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(points, frame.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }
}
