package bowling.domain.frame;

import bowling.domain.Playable;
import bowling.domain.Point;

public class Frame implements Playable {
    private Point firstPoint;
    private Point secondPoint;

    public Frame() {
        this.firstPoint = new Point();
        this.secondPoint = new Point();
    }

    @Override
    public boolean ended() {
        return striked() || secondPoint.played();
    }

    @Override
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

    @Override
    public boolean striked() {
        return firstPoint.striked();
    }

    @Override
    public boolean spared() {
        if (striked()) {
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
