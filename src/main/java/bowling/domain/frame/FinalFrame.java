package bowling.domain.frame;

import bowling.domain.Playable;
import bowling.domain.point.FinalPoints;

public class FinalFrame implements Playable {

    private FinalPoints finalPoints;

    public FinalFrame() {
        this.finalPoints = new FinalPoints();
    }

    @Override
    public boolean ended() {
        return finalPoints.ended();
    }

    @Override
    public void throwBall(int point) {
        finalPoints.throwBall(point);
    }

    @Override
    public boolean striked() {
        return finalPoints.striked();
    }

    @Override
    public boolean spared() {
        return finalPoints.spared();
    }

    public FinalPoints getFinalPoints() {
        return finalPoints;
    }
}
