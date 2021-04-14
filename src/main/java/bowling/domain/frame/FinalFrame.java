package bowling.domain.frame;

import bowling.domain.Playable;
import bowling.domain.Endable;
import bowling.domain.Point;

public class FinalFrame implements Playable, Endable {

    private static final int FRAME_COUNT = 10;

    private Frame frame;
    private Point bonusPoint;

    public FinalFrame() {
        this.frame = new Frame();
        this.bonusPoint = new Point();
    }

    @Override
    public boolean ended() {
        if (frame.isStrike() || frame.isSpare()) {
            return bonusPoint.played();
        }
        return frame.ended();
    }

    @Override
    public void throwBall(int point) {
        if ((frame.isStrike() || frame.isSpare()) && bonusPoint.played()) {
            return;
        }
        if ((frame.isStrike() || frame.isSpare()) && !bonusPoint.played()) {
            bonusPoint.throwBall(point);
            return;
        }
        if (frame.ended()) {
            return;
        }
        frame.throwBall(point);
    }

    public int frameCount() {
        return FRAME_COUNT;
    }

    public Frame frame() {
        return frame;
    }

    public Point bonusPoint(){
        return bonusPoint;
    }
}
