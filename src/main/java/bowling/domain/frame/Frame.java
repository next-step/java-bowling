package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.point.Points;
import bowling.domain.score.Score;
import bowling.domain.status.Status;

import java.util.Objects;

public abstract class Frame {
    protected Points points;
    protected Status status;
    protected Score score;

    public Frame() {
        points = new Points();
    }

    public abstract boolean isThrowable();

    public abstract void addScore();

    public Frame throwBall(Point point) throws OverThrowBallException{
        this.points.addPoint(point);
        this.status = this.status.throwBall(point);
        return this;
    }

    public Points getPoints() {
        return this.points;
    }

    public Integer getScore() {
        try {
            return score.getScore();
        } catch (Exception e) {
            return null;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void calculateScore(int point) {
        if (score != null) {
            this.score = score.calculate(point);
        }
    }

    public boolean hasScore() {
        return score != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(points, frame.points) &&
                Objects.equals(score, frame.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, score);
    }
}
