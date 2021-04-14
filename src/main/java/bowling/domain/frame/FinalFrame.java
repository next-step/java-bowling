package bowling.domain.frame;

import bowling.domain.BallThrowable;
import bowling.domain.Endable;

public class FinalFrame implements BallThrowable, Endable {
    private Frame frame;
    private Frame bonusFrame;

    public FinalFrame() {
        this.frame = new Frame();
        this.bonusFrame = new Frame();
    }

    @Override
    public boolean ended() {
        if (frame.isStrike() || frame.isSpare()) {
            return bonusFrame.ended();
        }
        return frame.ended();
    }

    @Override
    public void throwBall(int point) {
        if ((frame.isStrike() || frame.isSpare()) && bonusFrame.ended()) {
            return;
        }
        if ((frame.isStrike() || frame.isSpare()) && !bonusFrame.ended()) {
            bonusFrame.throwBall(point);
            return;
        }
        if (frame.ended()) {
            return;
        }
        frame.throwBall(point);
    }
}
