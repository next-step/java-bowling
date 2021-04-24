package bowling.domain.point;

import bowling.domain.Playable;

public class Points implements Playable {

    private Point firstPoint;
    private Point secondPoint;

    public Points() {
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

    public Point getFirstPoint() {
        return firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }
}
