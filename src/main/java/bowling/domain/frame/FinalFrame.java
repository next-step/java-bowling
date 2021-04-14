package bowling.domain.frame;

import bowling.domain.BallThrowable;
import bowling.domain.Endable;

public class FinalFrame implements BallThrowable, Endable {

    private static final int BONUS_FRAME_COUNT = 11;
    private static final int FRAME_COUNT = 10;

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

    public int frameCount() {
        if (bonusFrame.ended()) {
            return BONUS_FRAME_COUNT;
        }
        if (frame.isStrike() || frame.isSpare()) {
            return BONUS_FRAME_COUNT;
        }
        return FRAME_COUNT;
    }
}
