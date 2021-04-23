package bowling.domain.frame;

import bowling.domain.Playable;
import bowling.domain.point.Points;

public class Frame implements Playable {

    private Points points;

    public Frame() {
        this.points = new Points();
    }

    @Override
    public boolean ended() {
        return points.ended();
    }

    @Override
    public void throwBall(int point) {
        points.throwBall(point);
    }

    @Override
    public boolean striked() {
        return points.striked();
    }

    @Override
    public boolean spared() {
        return points.spared();
    }

    public Points getPoints() {
        return points;
    }
}
