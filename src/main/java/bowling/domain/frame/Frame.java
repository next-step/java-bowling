package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.score.NoneScoreException;
import bowling.domain.score.Score;
import bowling.domain.status.Status;

import java.util.Objects;

public abstract class Frame {
    protected Status status;
    protected Score score;

    public Frame() {
    }

    public void addScore() {
        try {
            this.score = status.getScore();
        } catch (NoneScoreException e) {
        }
    }

    public boolean isThrowable() {
        return !status.isEnd();
    }

    public Frame throwBall(Point point) throws OverThrowBallException {
        this.status = this.status.throwBall(point);
        return this;
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
        return Objects.equals(status, frame.status) &&
                Objects.equals(score, frame.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, score);
    }
}
