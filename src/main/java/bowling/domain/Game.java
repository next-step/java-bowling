package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrames;

public class Game {
    private NormalFrames normalFrames;
    private FinalFrame finalFrame;

    public Game() {
        this.normalFrames = new NormalFrames();
        this.finalFrame = new FinalFrame();
    }

    public void throwBall(int point) {
        if (!normalFrames.ended()) {
            normalFrames.throwBall(point);
            return;
        }
        if (!finalFrame.ended()) {
            finalFrame.throwBall(point);
        }
    }

    public boolean ended() {
        return normalFrames.ended() && finalFrame.ended();
    }

}
