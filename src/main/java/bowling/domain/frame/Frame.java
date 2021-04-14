package bowling.domain.frame;

import bowling.domain.BallThrowable;
import bowling.domain.Endable;
import bowling.domain.Point;

public class Frame implements BallThrowable, Endable {
    private Point firstPoint;
    private Point secondPoint;

    public Frame() {
        this.firstPoint = new Point();
        this.secondPoint = new Point();
    }

    public boolean ended() {
        return isStrike() || secondPoint.played();
    }

    public void throwBall(int point) {
        if (secondPoint.played()) {
            return;
        }
        if (firstPoint.played()) {
            secondPoint.throwBall(firstPoint, point);
            return;
        }
        firstPoint.throwBall(point);
    }

    public boolean isStrike() {
        return firstPoint.striked();
    }

    public boolean isSpare() {
        if (isStrike()) {
            return false;
        }
        return secondPoint.spared(this.firstPoint);
    }

    public Point firstPoint() {
        return firstPoint;
    }

    public Point secondPoint() {
        return secondPoint;
    }
}
