package bowling.domain.frame;

import bowling.domain.Endable;
import bowling.domain.Playable;

public class GameFrames implements Playable, Endable {
    private NormalFrames normalFrames;
    private FinalFrame finalFrame;

    public GameFrames() {
        this.normalFrames = new NormalFrames();
        this.finalFrame = new FinalFrame();
    }

    @Override
    public void throwBall(int point) {
        if (!normalFrames.ended()) {
            normalFrames.throwBall(point);
            return;
        }
        if (!finalFrame.ended()) {
            finalFrame.throwBall(point);
        }
    }

    @Override
    public boolean ended() {
        return normalFrames.ended() && finalFrame.ended();
    }

    public int frameCount() {
        if (normalFrames.ended()) {
            return finalFrame.frameCount();
        }
        return normalFrames.frameCount();
    }

    public NormalFrames normalFrames() {
        return normalFrames;
    }

    public FinalFrame finalFrame() {
        return finalFrame;
    }
}
